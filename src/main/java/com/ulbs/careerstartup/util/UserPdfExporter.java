package com.ulbs.careerstartup.util;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.ulbs.careerstartup.dto.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

import static com.ulbs.careerstartup.constant.Constants.DESCRIPTION;
import static com.ulbs.careerstartup.constant.Constants.NAME;

@RequiredArgsConstructor
@Setter
@Getter
public class UserPdfExporter {
    private final Map<String, String[]> fieldsOfInterest = Map.of(
            "JobHistoryDTO", new String[]{"position", "startDate", "endDate", DESCRIPTION, "company"},
            "CompanyDTO", new String[]{NAME, "address", "website"},
            "SpecializationDTO", new String[]{NAME, "startedDate", "finishDate", "degree", "faculty"},
            "FacultyDTO", new String[]{NAME, "address"},
            "ExperienceDTO", new String[]{"title", DESCRIPTION, "date", "type"},
            "SkillDTO", new String[]{NAME},
            "UserSkillsDTO", new String[]{"skillDTO", "proficiencyDTO"},
            "LanguageDTO", new String[]{NAME, "listening", "reading", "speaking", "conversation", "writing"},
            "ReferralDTO", new String[]{DESCRIPTION, "teacherDTO"},
            "UserDTO", new String[]{NAME, "email"}
    );

    private static String emailIconPath = "src/main/resources/favicons/email.png";
    private static String phoneIconPath = "src/main/resources/favicons/phone.png";
    private static String websiteIconPath = "src/main/resources/favicons/website.png";

    private static void drawBlueTriangleHeader(PdfDocument pdf, int pageNumber) {
        PdfPage page = pdf.getPage(pageNumber);
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdf);
        float width = pdf.getDefaultPageSize().getWidth();
        float height = pdf.getDefaultPageSize().getHeight();
        float margin = 20;
        float triangleHeight = 100;

        float[] xPoints = {margin, margin, width / 2};
        float[] yPoints = {height - margin - triangleHeight, height - margin, height - margin};

        canvas.setFillColor(ColorConstants.BLUE);
        canvas.setStrokeColor(ColorConstants.BLUE);

        canvas.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            canvas.lineTo(xPoints[i], yPoints[i]);
        }
        canvas.closePathFillStroke();
    }

    private static void addProfileImageInCircle(PdfPage page, String imagePath, float pageWidth, float pageHeight, float imgWidth) throws IOException {
        float x = pageWidth - imgWidth;
        float y = pageHeight - imgWidth;

        ImageData imageData = ImageDataFactory.create(imagePath);
        PdfCanvas canvas = new PdfCanvas(page);

        canvas.saveState();

        canvas.circle(x + imgWidth / 2, y + imgWidth / 2, imgWidth / 2);

        canvas.clip();
        canvas.endPath();

        canvas.addImage(imageData, x, y, imgWidth, false);

        canvas.restoreState();
    }

    private void addUserInfo(Document document, String text) {
        Paragraph p = new Paragraph(text).setFontColor(ColorConstants.BLACK);
        document.add(p);
    }

    private void addSection(Document document, String sectionTitle, Collection<?> items) {
        Paragraph p = new Paragraph(sectionTitle).setFontColor(ColorConstants.BLUE);
        document.add(p);
        for (Object item : items) {
            addDTOFields(document, item);
        }
    }

    private void addDTOFields(Document document, Object dto) {
        Class<?> clazz = dto.getClass();
        String className = clazz.getSimpleName();

        String[] fields = fieldsOfInterest.get(className);

        if (fields != null) {
            for (String fieldName : fields) {
                try {
                    String capitalizedFieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                    Method getter = clazz.getMethod("get" + capitalizedFieldName);
                    Object value = getter.invoke(dto);

                    if (value != null && fieldsOfInterest.containsKey(value.getClass().getSimpleName())) {
                        addDTOFields(document, value);
                    } else {
                        addUserInfo(document, fieldName + ": " + value);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public PdfDocument generateCv(UserDTO user) {
        try (PdfWriter writer = new PdfWriter(Files.newOutputStream(Path.of("src/main/resources/cv.pdf")))) {
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);

/* Image userImage = new Image(ImageDataFactory.create(user.getProfilePhoto().getContent()))
                .setWidth(100)
                .setHeight(100)
                .setFixedPosition(200, 700);
        document.add(userImage);*/
            PdfPage page = pdf.addNewPage();
            float pageWidth = page.getPageSize().getWidth();
            float pageHeight = page.getPageSize().getHeight();

            Image logo = new Image(ImageDataFactory.create("src/main/resources/images/img.png"))
                    .scaleToFit(220, 130)
                    .setFixedPosition(pageWidth - 120, pageHeight - 130);
            document.add(logo);
            PdfCanvas canvas = new PdfCanvas(page);

            float rectWidth = pageWidth / 3;
            canvas.setFillColor(new DeviceRgb(23, 46, 98))
                    .rectangle(0, 0, rectWidth, pageHeight)
                    .fill();

            String imagePath = "src/main/resources/default.jpg";
            float iconWidth =rectWidth/10;
            addProfileImageInCircle(page, imagePath, iconWidth, pageHeight, iconWidth);
            ImageData emailIcon = ImageDataFactory.create(emailIconPath);
            Image emailImage = new Image(emailIcon).setWidth(iconWidth).setAutoScale(true);
            Paragraph emailParagraph = new Paragraph().add(emailImage).add(new Text(user.getEmail()));
            addProfileImageInCircle(page, emailIconPath, iconWidth, pageHeight, iconWidth);
            addUserInfo(document, user.getEmail());
            addProfileImageInCircle(page, phoneIconPath, iconWidth, pageHeight, iconWidth);
            addUserInfo(document,  user.getPhone());
            addProfileImageInCircle(page, websiteIconPath, iconWidth, pageHeight + rectWidth+10, iconWidth);
            addUserInfo(document, "Website: " + user.getWebsite());
            float columnWidth = 2 * (pageWidth / 3) - 70;
            float marginLeft = pageWidth / 3 + 30;
            float marginTop = 100;
            float marginRight = 20;
            float marginBottom = 20;

            Rectangle[] columns = {new Rectangle(marginLeft, marginBottom, columnWidth, pageHeight - marginTop - marginBottom)};
            document.setRenderer(new ColumnDocumentRenderer(document, columns));

            addUserInfo(document, "Name: " + user.getName());
            addUserInfo(document, "Description: " + user.getDescription());

            addSection(document, "Education", user.getSpecializationsDTO());
            addSection(document, "Job History", user.getJobHistoriesDTO());
            addSection(document, "Experience", user.getExperiencesDTO());
            addSection(document, "Skills", user.getSkillsDTO());
            //  addSection(document, "Languages", user.getLanguagesDTO());
            addSection(document, "Referral", user.getReceivedReferralsDTO());
            document.close();
            return pdf;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createPageTemplate() {

    }
}