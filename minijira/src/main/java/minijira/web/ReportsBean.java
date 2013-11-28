package minijira.web;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import ejb.database.model.Project;
import ejb.database.model.Status;
import ejb.database.model.Task;
import ejb.util.Log;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Alexx
 * Date: 24.11.13
 * Time: 15:47
 * Email: alexey.gorovoy.work@gmail.com
 */
@Named("reports")
@SessionScoped
public class ReportsBean implements Serializable {

    @Inject
    DatabaseBean databaseBean;

    @Inject
    AuthBean authBean;

    String bundlename;
    Locale locale;
    ResourceBundle bundle;

    private void init() {
        bundlename = "minijira.locale.ResourceBundle";
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        bundle = ResourceBundle.getBundle(bundlename, locale);
    }
    private Font getFont(int size, int red, int green, int blue) {
        Font font = null;
        try {
            font = new Font(BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), size);
            font.setColor(red, green, blue);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  catch (DocumentException ex) {
            ex.printStackTrace();
        } finally {
            if (font == null) {
                font = new Font();
            }
        }
        return font;
    }
    public void prepareReport1() {
        init();

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {

            String fileLocation = "C:\\ITextTest.pdf";

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));

            document.open();

            java.util.List<Project> projectList = databaseBean.getProjects();
            //java.util.List<Section> sections = new LinkedList<Section>();

            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            //paragraph1.add(anchorTarget);
            document.add(paragraph1);

            Paragraph title1 = new Paragraph(bundle.getString("reports.report1Title"), getFont(18,47, 150, 140));
            Chapter chapter = new Chapter(title1, 1);

            chapter.setNumberDepth(0);

            for (Project project : projectList) {

                Paragraph projectTitle = new Paragraph(project.getTitle(), getFont(14, 81, 163, 81));

                Section section1 = chapter.addSection(projectTitle);

                int completed = 0;
                int all = 0;

                for (Status status : databaseBean.getStatuses()) {
                    int num = databaseBean.getDc().calculateTask(project, status);

                    if (status.getId() == 3 || status.getId() == 4) {
                        completed += num;
                    }
                    all += num;

                    Paragraph someSectionText = new Paragraph(" - " + status.getTitle() + "     " + num, getFont(14, 0,0,0));
                    section1.add(someSectionText);
                }

                Paragraph someSectionText = new Paragraph(bundle.getString("reports.all") + ": " + all + ".", getFont(14, 0,0,0));
                section1.add(someSectionText);

                float percent = (all == 0) ? 0 : ((float)completed/all)*100;
                someSectionText = new Paragraph(bundle.getString("reports.completion") + ": " + percent + "%.", getFont(14, 0,0,0));
                section1.add(someSectionText);

            }

            document.add(chapter);

            document.close();
            writer.close();

            download(bundle.getString("reports.report1Filename"), fileLocation);

        } catch (Exception ex) {
            Log.getLogger().info("face exception while report preparing");
            ex.printStackTrace();
        }
    }

    public void prepareReport2() {

        init();

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);

        try {

            String fileLocation = "C:\\ITextTest2.pdf";

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));

            document.open();

            java.util.List<Project> projectList = databaseBean.getProjects();
            //java.util.List<Section> sections = new LinkedList<Section>();

            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(50);

            //paragraph1.add(anchorTarget);
            document.add(paragraph1);

            Paragraph title1 = new Paragraph(bundle.getString("reports.report2Title"),  getFont(18,47, 150, 140));
            Chapter chapter = new Chapter(title1, 1);

            chapter.setNumberDepth(0);

            for (Task task : databaseBean.getDc().findTasksToBeDone(authBean.getEmployee())) {

                Paragraph projectTitle = new Paragraph(task.getTitle(),  getFont(14, 81, 163, 81));

                Section section1 = chapter.addSection(projectTitle);

                Paragraph someSectionText = new Paragraph(bundle.getString("reports.project") + ": " + task.getProject().getTitle()
                        + " (" + task.getProject().getType().getTitle() +")", getFont(14, 0,0,0));
                section1.add(someSectionText);


                someSectionText = new Paragraph(bundle.getString("reports.reporter") + ": " + task.getReporter().getName()
                                                                       + task.getReporter().getSurname()
                                                                       + " ("+ task.getReporter().getEmail() +")", getFont(14, 0,0,0));
                section1.add(someSectionText);


                someSectionText = new Paragraph(bundle.getString("reports.description") + ": " + task.getDescription() + ".", getFont(14, 0,0,0));
                section1.add(someSectionText);

                someSectionText = new Paragraph(bundle.getString("reports.priority") + ": " + task.getPriority().getTitle() + ".", getFont(14, 0,0,0));
                section1.add(someSectionText);

                someSectionText = new Paragraph(bundle.getString("reports.status") + ": " + task.getStatus().getTitle() + ".", getFont(14, 0,0,0));
                section1.add(someSectionText);

                someSectionText = new Paragraph(bundle.getString("reports.posted") + ": " + (new SimpleDateFormat("dd-MM-yyyy").format(task.getDueto()))+ ".", getFont(14, 0,0,0));
                section1.add(someSectionText);
            }

            document.add(chapter);

            document.close();
            writer.close();

            download(bundle.getString("reports.report2Filename"), fileLocation);

        } catch (Exception ex) {
            Log.getLogger().info("face exception while report preparing");
            ex.printStackTrace();
        }
    }


    public void download(String fileName, String fileLocation) throws IOException {
        File file = new File(fileLocation);
        FileInputStream fis = new FileInputStream(file);

        ///// Downloading ....

        int contentLength = (int)file.length();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.

        ec.setResponseContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.

        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        OutputStream output = ec.getResponseOutputStream();
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
        // ...

        while (fis.available() > 0) {
            byte[] bytes = new byte[1000];
            int readed = fis.read(bytes);
            output.write(bytes);
            output.flush();
        }

        fis.close();
        output.flush();

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.


     }

}
