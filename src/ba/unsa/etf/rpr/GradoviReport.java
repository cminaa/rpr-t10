package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class GradoviReport {
    public void showReport(ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(GeografijaDAO.getInstance().getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void save( String fileName, Connection c ) throws JRException, IOException {
        String reportSrcFile = getClass().getResource("/reports/TutorijalReport.jrxml").getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, c );
        File f = new File( fileName );
        OutputStream outputStream = new FileOutputStream(f);
        if ( fileName.contains(".docx") ) {
            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(f));
            exporter.exportReport();
        }
        if ( fileName.contains(".pdf") )
            JasperExportManager.exportReportToPdfStream(print, outputStream);
        if ( fileName.contains(".xslx") ) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(f));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }
        outputStream.close();
    }

}
