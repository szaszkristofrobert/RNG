package com.company;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

public class WeatherAPI {

    public void submittingForm(int stateIndex, String date) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38))  {

            webClient.getOptions().setCssEnabled(true);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(10000);

            // Get the first page
            final HtmlPage page = webClient.getPage("https://nowdata.rcc-acis.org/iln/");

            //String text = page.asText();
            //System.out.println(text);

            //allam kivalasztasa
            final HtmlSelect selectState = page.getElementByName("station");
            selectState.setSelectedIndex(stateIndex);

            //datum kivalasztasa
            final HtmlInput dateInput = page.getHtmlElementById("tDatepicker");
            dateInput.setTextContent(date);

            //go gomb megnyomasa
            final HtmlButton button = page.getHtmlElementById("go");
            webClient.waitForBackgroundJavaScript(10000);
            button.click();
            webClient.waitForBackgroundJavaScript(10000);
            button.fireEvent("click");

            webClient.waitForBackgroundJavaScript(10000);
            //Thread.sleep(10000);

            final HtmlDivision resultsArea = page.getHtmlElementById("results_area");

            final HtmlTable resultsTable = (HtmlTable) resultsArea.getChildNodes().get(0);
            for (final HtmlTableRow row : resultsTable.getRows()) {
                System.out.println("Found row");
                for (final HtmlTableCell cell : row.getCells()) {
                    System.out.println("   Found cell: " + cell.asText());
                }
            }

        }
    }
}