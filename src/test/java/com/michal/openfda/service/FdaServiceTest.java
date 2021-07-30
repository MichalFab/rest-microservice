package com.michal.openfda.service;

import com.michal.openfda.dto.fdaapi.response.Result;
import com.michal.openfda.exceptions.FdaConnectionException;
import com.michal.openfda.service.fda.FdaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
@TestPropertySource(properties = {
        "fda.url=https://mocked-fda.com/drugsfda.json",
})
class FdaServiceTest {

    @Autowired
    private FdaService fdaService;
    private MockRestServiceServer mockServer;
    @Value("${fda.url}")
    String fdaMockedUrl;
    @Autowired
    private RestTemplate restTemplate;


    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
    }


    @Test
    @DisplayName("Should find, parse and return the correct result")
    void shouldReturnCorrectResult() throws FdaConnectionException {
//        given
        String manufacturerName = "pfizer";
        String applicationName = "NDA012616";
//        when
        mockServer.expect(ExpectedCount.once(),
                requestTo(fdaMockedUrl + "?search=openfda.manufacturer_name%253D%2522" + manufacturerName + "%2522&limit=1&skip=0"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{ \"meta\": { \"disclaimer\": \"Do not rely on openFDA to make decisions regarding medical care. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated. We may limit or otherwise restrict your access to the API in line with our Terms of Service.\", \"terms\": \"https://open.fda.gov/terms/\", \"license\": \"https://open.fda.gov/license/\", \"last_updated\": \"2021-07-27\", \"results\": { \"skip\": 0, \"limit\": 1, \"total\": 124 } }, \"results\": [ { \"submissions\": [ { \"submission_type\": \"SUPPL\", \"submission_number\": \"58\", \"submission_status\": \"AP\", \"submission_status_date\": \"19971113\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"72\", \"submission_status\": \"AP\", \"submission_status_date\": \"20130614\", \"submission_class_code\": \"MANUF (CMC)\", \"submission_class_code_description\": \"Manufacturing (CMC)\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"62\", \"submission_status\": \"AP\", \"submission_status_date\": \"19990421\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"16\", \"submission_status\": \"AP\", \"submission_status_date\": \"19780104\", \"submission_class_code\": \"REMS\", \"submission_class_code_description\": \"REMS\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"69\", \"submission_status\": \"AP\", \"submission_status_date\": \"20110922\", \"review_priority\": \"UNKNOWN\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"12023\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2011/012616s069ltr.pdf\", \"date\": \"20110926\", \"type\": \"Letter\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"75\", \"submission_status\": \"AP\", \"submission_status_date\": \"20140306\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"12026\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2014/012616Orig1s075ltr.pdf\", \"date\": \"20140307\", \"type\": \"Letter\" }, { \"id\": \"33089\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2014/012616s075lbl.pdf\", \"date\": \"20140307\", \"type\": \"Label\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"81\", \"submission_status\": \"AP\", \"submission_status_date\": \"20210219\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"66316\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2021/012616s081lbl.pdf\", \"date\": \"20210222\", \"type\": \"Label\" }, { \"id\": \"66323\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2021/012616Orig1s081ltr.pdf\", \"date\": \"20210223\", \"type\": \"Letter\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"73\", \"submission_status\": \"AP\", \"submission_status_date\": \"20130612\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"10817\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2013/012616s073lbl.pdf\", \"date\": \"20130618\", \"type\": \"Label\" }, { \"id\": \"12024\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2013/012616Orig1s073ltr.pdf\", \"date\": \"20130613\", \"type\": \"Letter\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"63\", \"submission_status\": \"AP\", \"submission_status_date\": \"20021126\", \"submission_class_code\": \"MANUF (CMC)\", \"submission_class_code_description\": \"Manufacturing (CMC)\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"55\", \"submission_status\": \"AP\", \"submission_status_date\": \"19900123\", \"submission_class_code\": \"MANUF (CMC)\", \"submission_class_code_description\": \"Manufacturing (CMC)\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"61\", \"submission_status\": \"AP\", \"submission_status_date\": \"19990113\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"37\", \"submission_status\": \"AP\", \"submission_status_date\": \"19971113\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\" }, { \"submission_type\": \"ORIG\", \"submission_number\": \"1\", \"submission_status\": \"AP\", \"submission_status_date\": \"19610127\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"TYPE 4\", \"submission_class_code_description\": \"Type 4 - New Combination\" }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"78\", \"submission_status\": \"AP\", \"submission_status_date\": \"20190117\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"57247\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2019/012616s078lbl.pdf\", \"date\": \"20190118\", \"type\": \"Label\" }, { \"id\": \"57249\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2019/012616Orig1s078ltr.pdf\", \"date\": \"20190118\", \"type\": \"Letter\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"76\", \"submission_status\": \"AP\", \"submission_status_date\": \"20141022\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"10818\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2014/012616s076lbl.pdf\", \"date\": \"20141024\", \"type\": \"Label\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"68\", \"submission_status\": \"AP\", \"submission_status_date\": \"20110519\", \"review_priority\": \"UNKNOWN\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"10816\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2011/012616s068lbl.pdf\", \"date\": \"20110523\", \"type\": \"Label\" }, { \"id\": \"12022\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2011/012616s068ltr.pdf\", \"date\": \"20110524\", \"type\": \"Letter\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"74\", \"submission_status\": \"AP\", \"submission_status_date\": \"20140117\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"12025\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2014/012616Orig1s074ltr.pdf\", \"date\": \"20140121\", \"type\": \"Letter\" }, { \"id\": \"33088\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2014/012616s074lbl.pdf\", \"date\": \"20140122\", \"type\": \"Label\" } ] }, { \"submission_type\": \"SUPPL\", \"submission_number\": \"80\", \"submission_status\": \"AP\", \"submission_status_date\": \"20200820\", \"review_priority\": \"STANDARD\", \"submission_class_code\": \"LABELING\", \"submission_class_code_description\": \"Labeling\", \"application_docs\": [ { \"id\": \"64388\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2020/012616Orig1s080ltr.pdf\", \"date\": \"20200824\", \"type\": \"Letter\" }, { \"id\": \"64435\", \"url\": \"http://www.accessdata.fda.gov/drugsatfda_docs/label/2020/012616s080lbl.pdf\", \"date\": \"20200827\", \"type\": \"Label\" } ] } ], \"application_number\": \"NDA012616\", \"sponsor_name\": \"PFIZER\", \"openfda\": { \"application_number\": [ \"NDA012616\" ], \"brand_name\": [ \"ALDACTAZIDE\", \"SPIRONOLACTONE AND HYDROCHLOROTHIAZIDE\" ], \"generic_name\": [ \"SPIRONOLACTONE AND HYDROCHLOROTHIAZIDE\" ], \"manufacturer_name\": [ \"Pfizer Laboratories Div Pfizer Inc\", \"Greenstone LLC\" ], \"product_ndc\": [ \"0025-1011\", \"0025-1021\", \"59762-5014\" ], \"product_type\": [ \"HUMAN PRESCRIPTION DRUG\" ], \"route\": [ \"ORAL\" ], \"substance_name\": [ \"SPIRONOLACTONE\", \"HYDROCHLOROTHIAZIDE\" ], \"rxcui\": [ \"198224\", \"198225\", \"208112\", \"208116\" ], \"spl_id\": [ \"2e3846d9-aed6-41a5-a271-daa792be3128\", \"c3873fcc-6174-420a-b560-ca964ee0c726\" ], \"spl_set_id\": [ \"d44637a7-b980-4bb1-832c-f5a0f73f8259\", \"624f0563-8c94-40e9-8ed0-8cc14950bad2\" ], \"package_ndc\": [ \"0025-1011-31\", \"0025-1021-31\", \"59762-5014-1\" ], \"nui\": [ \"N0000175359\", \"N0000175419\", \"M0471776\", \"N0000175557\", \"N0000011310\" ], \"pharm_class_pe\": [ \"Increased Diuresis [PE]\" ], \"pharm_class_epc\": [ \"Thiazide Diuretic [EPC]\", \"Aldosterone Antagonist [EPC]\" ], \"pharm_class_cs\": [ \"Thiazides [CS]\" ], \"pharm_class_moa\": [ \"Aldosterone Antagonists [MoA]\" ], \"unii\": [ \"0J48LPH2TH\", \"27O7W4T232\" ] }, \"products\": [ { \"product_number\": \"005\", \"reference_drug\": \"Yes\", \"brand_name\": \"ALDACTAZIDE\", \"active_ingredients\": [ { \"name\": \"HYDROCHLOROTHIAZIDE\", \"strength\": \"50MG\" }, { \"name\": \"SPIRONOLACTONE\", \"strength\": \"50MG\" } ], \"reference_standard\": \"Yes\", \"dosage_form\": \"TABLET\", \"route\": \"ORAL\", \"marketing_status\": \"Prescription\" }, { \"product_number\": \"004\", \"reference_drug\": \"Yes\", \"brand_name\": \"ALDACTAZIDE\", \"active_ingredients\": [ { \"name\": \"HYDROCHLOROTHIAZIDE\", \"strength\": \"25MG\" }, { \"name\": \"SPIRONOLACTONE\", \"strength\": \"25MG\" } ], \"reference_standard\": \"No\", \"dosage_form\": \"TABLET\", \"route\": \"ORAL\", \"marketing_status\": \"Prescription\", \"te_code\": \"AB\" } ] } ] }")
                );
//      then
        PageImpl<Result> result = fdaService.findApplications(manufacturerName, null, PageRequest.of(0, 1));
        mockServer.verify();
        assertThat(result.getTotalElements()).isGreaterThan(0);
        assertThat(result.get().allMatch(application -> application.getApplicationNumber().equals(applicationName))).isTrue();
        assertThat(result.get().allMatch(application -> application.getProducts().size() == 2)).isTrue();
    }

    @Test
    @DisplayName("Should return empty result if the FDA found no data")
    void shouldReturnEmptyResult() throws FdaConnectionException {
//        given
        String manufacturerName = "no_data";
//        when
        mockServer.expect(ExpectedCount.once(),
                requestTo(fdaMockedUrl + "?search=openfda.manufacturer_name%253D%2522" + manufacturerName + "%2522&limit=1&skip=0"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("")
                );
//      then
        PageImpl<Result> result = fdaService.findApplications(manufacturerName, null, PageRequest.of(0, 1));
        mockServer.verify();
        assertThat(result.getTotalElements()).isZero();
    }

    @Test
    @DisplayName("Should throw FdaConnectionException if there is a problem connecting to the FDA API")
    void shouldThrowFdaConnectionException() {
//        given
        String manufacturerName = "bad_data";
//        when
        mockServer.expect(ExpectedCount.once(),
                requestTo(fdaMockedUrl + "?search=openfda.manufacturer_name%253D%2522" + manufacturerName + "%2522&limit=1&skip=0"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE)
                );
//      then
        assertThrows(
                FdaConnectionException.class,
                () -> fdaService.findApplications(manufacturerName, null, PageRequest.of(0, 1))
        );
    }

    @Test
    @DisplayName("Should correctly construct the URL based on the given parameters")
    void shouldConstructCorrectUrl() throws FdaConnectionException {
//        given
        String manufacturerName = "pfizer";
        String brandName = "apple";
//        when
        mockServer.expect(ExpectedCount.once(),
                requestTo(fdaMockedUrl + "?search=openfda.manufacturer_name%253D%2522" + manufacturerName + "%2522+AND+openfda.brand_name%253D%2522" + brandName + "%2522&limit=1&skip=0"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("")
                );
//      then
        fdaService.findApplications(manufacturerName, brandName, PageRequest.of(0, 1));
        mockServer.verify();
    }
}