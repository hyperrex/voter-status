package status;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//import java.util.Map;
//import org.jsoup.Jsoup;
//import org.jsoup.Connection.Method;
//import org.jsoup.Connection.Response;
//import org.jsoup.nodes.Document;

@RestController
public class StatusController {

    @CrossOrigin (origins = "*")
    @RequestMapping("/status")
    public Status status(@RequestParam(value="name", defaultValue="World on FIRE") String name) {

//        try{
//
//            Response response =
//                    Jsoup.connect("https://www.acgov.org/alco_ssl_app/rov/voter_info/voter_profile.jsp?formLanguage=E")
//                            .userAgent("Mozilla/5.0")
//                            .timeout(10 * 1000)
//                            .method(Method.POST)
//                            .data("#dob_mm", "07")
//                            .data("#dob_dd", "19")
//                            .data("#dob_yy", "1971")
//                            .data("#ssn", "0450")
//                            .followRedirects(true)
//                            .execute();
//
//            //parse the document from response
//            Document document = response.parse();
//            System.out.println("Document");
//            System.out.println(document);
//        return new Status(true);
//
//    } catch(IOException ioe) {
//        System.out.println("Exception: " + ioe);
//
//        return new Status(false);
//    }

        // NEW CODE
            // use htmlunit to hit the same endpoint as above with the same data
            // print the response page


        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60)) {

            webClient.getOptions().setCssEnabled(true);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());

            // Get the first page
            final HtmlPage page1 = webClient.getPage("https://www.acgov.org/alco_ssl_app/rov/voter_info/voter_profile.jsp");
            webClient.waitForBackgroundJavaScript(30 * 1000); /* will wait JavaScript to execute up to 30s */

            System.out.println(page1);
//            Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.

//            final HtmlDivision form = page1.getHtmlElementById("login_form");

              final List form = page1.getByXPath("//div[contains(@class, 'login_form')]");
              System.out.println(form);
//            final HtmlTextInput dob_mm = form.getInputByName("#dob_mm");
//            final HtmlTextInput dob_dd = form.getInputByName("#dob_dd");
//            final HtmlTextInput dob_yy = form.getInputByName("#dob_yy");
//            final HtmlTextInput ssn = form.getInputByName("#ssn");
//            final HtmlSubmitInput button = form.getInputByName("SUBMIT");
//
            // Change the value of the text field
//            dob_mm.type("07");
//            dob_dd.type("19");
//            dob_yy.type("1971");
//            ssn.type("0450");

            // Now submit the form by clicking the button and get back the second page.
//            final HtmlPage page2 = button.click();

        } catch(IOException ioe) {

            System.out.println("Exception: " + ioe);

            return new Status(false);
    }

    return new Status(true);

    }

}
