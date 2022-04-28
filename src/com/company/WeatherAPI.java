package com.company;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.BufferedReader;
import java.io.FileWriter;

public class WeatherAPI {
    FileManager fm = new FileManager();

    public void submittingForm(int stateIndex, String year, String outputname, int stateNum) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38))  {

            FileWriter myWriter = fm.getWeatherWriter(outputname);
            webClient.getOptions().setCssEnabled(true);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(10000);

            // Get the first page
            HtmlPage page = webClient.getPage("https://nowdata.rcc-acis.org/iln/");

            //String text = page.asText();
            //System.out.println(text);

            //allam kivalasztasa
            webClient.waitForBackgroundJavaScript(500);
            HtmlSelect selectState = page.getElementByName("station");
            HtmlOption option = selectState.getOption(stateIndex);
            selectState.setSelectedAttribute(option, true);
            //System.out.println(selectState.getOptions());

            //datum kivalasztasa
            final HtmlTextInput dateInput = page.getHtmlElementById("tDatepicker");
            String[] months = {"-01", "-02", "-03", "-04", "-05", "-06", "-07", "-08", "-09", "-10", "-11", "-12",};
            for(int i = 0; i < 12; i++) {
                dateInput.setValueAttribute(year+months[i]);
                float percentage = 100*(((float) stateIndex)*12 + ((float)i+1))/(stateNum*12);


                //go gomb megnyomasa
                final HtmlButton button = page.getHtmlElementById("go");
                //webClient.waitForBackgroundJavaScript(2500);
                button.click();
                //webClient.waitForBackgroundJavaScript(2500);
                button.fireEvent("click");
                webClient.waitForBackgroundJavaScript(4000);

                final HtmlDivision resultsArea = page.getHtmlElementById("results_area");

                int iter = 0;
                while (resultsArea.getChildNodes().get(0) == null || resultsArea.getChildNodes().get(0).getClass() == com.gargoylesoftware.htmlunit.html.HtmlDivision.class){
                    if (iter>20){
                        System.out.println("Tul sokat vart js-re");
                        break;
                    }
                    webClient.waitForBackgroundJavaScript(500);
                    iter++;
                }

                final HtmlTable resultsTable = (HtmlTable) resultsArea.getChildNodes().get(0);
                HtmlTableRow row;
                for (int j = 2; j < resultsTable.getRowCount(); j++){
                    row = resultsTable.getRow(j);
                    for (final HtmlTableCell cell : row.getCells()) {
                        myWriter.write("\t" + cell.asText());
                    }
                    myWriter.write("\n");

                }
                System.out.println(year+months[i] + "\t" + option.getValueAttribute() + "\t" + percentage + "%");
            }
            myWriter.close();
        }
    }
}