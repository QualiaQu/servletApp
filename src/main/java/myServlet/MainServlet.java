package myServlet;

import myServlet.FileDescription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String parameter = req.getParameter("path");
        String defaultPath = System.getProperty("user.home");
        String openPath;
        if(parameter == null || parameter.isEmpty()){
            openPath = defaultPath;
        }else{
            openPath = parameter;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        File file = new File(openPath);
        File[] files = file.listFiles();
        if (files == null)
            resp.sendError(404, "File not found");

        SimpleDateFormat fileFormat = new SimpleDateFormat("d/M/yy, K:mm:ss a");
        List<FileDescription> fileDescriptionList = new ArrayList<>();
        for (File tempFile : files) {
            fileDescriptionList.add(new FileDescription(tempFile.getName(),
                    openPath + "\\" + tempFile.getName(),
                    String.valueOf(tempFile.length()),
                    fileFormat.format(new Date(tempFile.lastModified())),
                    tempFile.isDirectory()));
        }
        req.setAttribute("date", LocalDateTime.now().format(formatter));
        req.setAttribute("back", file.getParent());
        req.setAttribute("name", openPath);
        req.setAttribute("temp", fileDescriptionList.toArray());
        req.getRequestDispatcher("main.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}