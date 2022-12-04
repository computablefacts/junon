package com.computablefacts.junon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.Map;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

public class TestFact {

  @Test
  public void testHashcodeAndEquals() {
    EqualsVerifier.forClass(Fact.class).withIgnoredFields("id_", "externalId_").verify();
  }

  @Test
  public void testFromLegacy() throws Exception {

    String json = "{\n" + "  \"id\": 706396,\n" + "  \"type\": \"evenements_garantis_degats_eaux_limites_v2\",\n"
        + "  \"values\": [\n" + "    \"3032-0006\",\n" + "    \"35594Z\",\n" + "    \"3\"\n" + "  ],\n"
        + "  \"is_valid\": false,\n" + "  \"confidence_score\": 0.71073985303466,\n"
        + "  \"external_id\": \"PizD9|2021-11-22T08:41:24.096Z\",\n" + "  \"start_date\": \"2021-11-22T08:41:24Z\",\n"
        + "  \"end_date\": \"2021-11-22T08:41:40Z\",\n" + "  \"metadata\": [\n" + "    {\n" + "      \"id\": 192517,\n"
        + "      \"type\": \"ExtractionTool\",\n" + "      \"key\": \"extracted_by_user_name\",\n"
        + "      \"value\": \"John DOE\"\n" + "    },\n" + "    {\n" + "      \"id\": 192518,\n"
        + "      \"type\": \"ExtractionTool\",\n" + "      \"key\": \"extracted_by_user_email\",\n"
        + "      \"value\": \"jdoe@example.com\"\n" + "    },\n" + "    {\n" + "      \"id\": 193366,\n"
        + "      \"type\": \"Comment\",\n" + "      \"key\": \"extracted_with\",\n" + "      \"value\": \"morta\"\n"
        + "    },\n" + "    {\n" + "      \"id\": 193367,\n" + "      \"type\": \"Comment\",\n"
        + "      \"key\": \"extracted_by\",\n" + "      \"value\": \"CoreApi\"\n" + "    },\n" + "    {\n"
        + "      \"id\": 212231,\n" + "      \"type\": \"Comment\",\n" + "      \"key\": \"extraction_date\",\n"
        + "      \"value\": \"2021-11-22T08:41:24.096Z\"\n" + "    }\n" + "  ],\n" + "  \"provenances\": [\n"
        + "    {\n" + "      \"source_store\": \"ACCUMULO/client_prod/dab/gcqcl|2021-01-27T23:23:45.006Z\",\n"
        + "      \"source_type\": \"STORAGE/ROOT/DATASET/DOC_ID\",\n" + "      \"string_span\": \"charges ﬁscales\",\n"
        + "      \"string_span_hash\": \"46df8445df964303047b8b5089e498d9\",\n" + "      \"start_index\": 0,\n"
        + "      \"end_index\": 15,\n" + "      \"source\": {\n" + "        \"storage\": \"ACCUMULO\",\n"
        + "        \"root\": \"client_prod\",\n" + "        \"dataset\": \"dab\",\n"
        + "        \"doc_id\": \"gcqcl|2021-01-27T23:23:45.006Z\",\n" + "        \"resource\": \"resource\",\n"
        + "        \"resource_link\": \"https://www.client.computablefacts.com/search/document/gcqcl|2021-01-27T23:23:45.006Z\"\n"
        + "      }\n" + "    }\n" + "  ],\n" + "  \"updated_at\": \"22-11-2021\",\n" + "  \"value_0\": \"3032-0006\"\n"
        + "}\n";

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> obj = mapper.readValue(json, TypeFactory.defaultInstance().constructType(Map.class));
    Fact fact = Fact.fromLegacy(obj);

    Assert.assertNotNull(fact);
    Assert.assertEquals(706396, fact.id_);
    Assert.assertEquals("PizD9|2021-11-22T08:41:24.096Z", fact.externalId_);
    Assert.assertEquals("evenements_garantis_degats_eaux_limites_v2", fact.type_);
    Assert.assertNull(fact.authorizations_);
    Assert.assertFalse(fact.isValid_);
    Assert.assertEquals(0.71073985303466, fact.confidenceScore_, 0.0000000001);
    Assert.assertEquals("2021-11-22T08:41:24Z", fact.startDate_);
    Assert.assertEquals("2021-11-22T08:41:40Z", fact.endDate_);

    Assert.assertEquals(3, fact.values_.size());
    Assert.assertEquals(1, fact.provenances_.size());
    Assert.assertEquals(5, fact.metadata_.size());

    Assert.assertEquals("3032-0006", fact.values_.get(0));
    Assert.assertEquals("35594Z", fact.values_.get(1));
    Assert.assertEquals("3", fact.values_.get(2));

    Assert.assertEquals("ACCUMULO/client_prod/dab/gcqcl|2021-01-27T23:23:45.006Z",
        fact.provenances_.get(0).sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", fact.provenances_.get(0).sourceType_);
    Assert.assertNull(fact.provenances_.get(0).sourceReliability_);
    Assert.assertNull(fact.provenances_.get(0).string_);
    Assert.assertEquals("charges ﬁscales", fact.provenances_.get(0).span_);
    Assert.assertEquals("46df8445df964303047b8b5089e498d9", fact.provenances_.get(0).spanHash_);
    Assert.assertEquals(0, (int) fact.provenances_.get(0).startIndex_);
    Assert.assertEquals(15, (int) fact.provenances_.get(0).endIndex_);
    Assert.assertEquals(3, (int) fact.provenances_.get(0).page_);

    Assert.assertEquals("ExtractionTool", fact.metadata_.get(0).type());
    Assert.assertEquals("extracted_by_user_name", fact.metadata_.get(0).key());
    Assert.assertEquals("John DOE", fact.metadata_.get(0).value());
  }

  @Test
  public void testConstructorWith2Params() {

    Fact fact = new Fact("evenements_garantis_degats_eaux_limites_v2", 0.5);

    Assert.assertNotNull(fact);
    Assert.assertEquals(0, fact.id_);
    Assert.assertTrue(fact.externalId_.matches("^[a-zA-Z0-9]{5}\\|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertEquals("evenements_garantis_degats_eaux_limites_v2", fact.type_);
    Assert.assertNull(fact.authorizations_);
    Assert.assertNull(fact.isValid_);
    Assert.assertEquals(0.5, fact.confidenceScore_, 0.0000000001);
    Assert.assertTrue(fact.startDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertNull(fact.endDate_);

    Assert.assertEquals(0, fact.values_.size());
    Assert.assertEquals(0, fact.provenances_.size());
    Assert.assertEquals(0, fact.metadata_.size());

    fact.value("123");
    fact.value("456");

    Assert.assertEquals(2, fact.values_.size());
    Assert.assertEquals("123", fact.values_.get(0));
    Assert.assertEquals("456", fact.values_.get(1));

    fact.metadata(Lists.newArrayList(new Metadata("info", "author", "patrick")));
    fact.metadata(new Metadata("info", "date", "2020-03-17"));

    Assert.assertEquals(2, fact.metadata_.size());
    Assert.assertEquals("info", fact.metadata_.get(0).type());
    Assert.assertEquals("author", fact.metadata_.get(0).key());
    Assert.assertEquals("patrick", fact.metadata_.get(0).value());
    Assert.assertEquals("info", fact.metadata_.get(1).type());
    Assert.assertEquals("date", fact.metadata_.get(1).key());
    Assert.assertEquals("2020-03-17", fact.metadata_.get(1).value());

    fact.provenance(
        new Provenance("STORAGE/ROOT/DATASET/DOC_ID", "ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z",
            "High", new Date(), new Date()));

    Assert.assertEquals(1, fact.provenances_.size());
    Assert.assertEquals("ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z",
        fact.provenances_.get(0).sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", fact.provenances_.get(0).sourceType_);
    Assert.assertEquals("High", fact.provenances_.get(0).sourceReliability_);
    Assert.assertNull(fact.provenances_.get(0).string_);
    Assert.assertNull(fact.provenances_.get(0).span_);
    Assert.assertNull(fact.provenances_.get(0).spanHash_);
    Assert.assertNull(fact.provenances_.get(0).startIndex_);
    Assert.assertNull(fact.provenances_.get(0).endIndex_);
    Assert.assertNull(fact.provenances_.get(0).page_);
  }

  @Test
  public void testConstructorWith3Params() {

    Fact fact = new Fact("evenements_garantis_degats_eaux_limites_v2", 0.5, "JOB_123");

    Assert.assertNotNull(fact);
    Assert.assertEquals(0, fact.id_);
    Assert.assertTrue(fact.externalId_.matches("^[a-zA-Z0-9]{5}\\|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertEquals("evenements_garantis_degats_eaux_limites_v2", fact.type_);
    Assert.assertEquals("JOB_123", fact.authorizations_);
    Assert.assertNull(fact.isValid_);
    Assert.assertEquals(0.5, fact.confidenceScore_, 0.0000000001);
    Assert.assertTrue(fact.startDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertNull(fact.endDate_);

    Assert.assertEquals(0, fact.values_.size());
    Assert.assertEquals(0, fact.provenances_.size());
    Assert.assertEquals(0, fact.metadata_.size());

    fact.value("123");
    fact.value("456");

    Assert.assertEquals(2, fact.values_.size());
    Assert.assertEquals("123", fact.values_.get(0));
    Assert.assertEquals("456", fact.values_.get(1));

    fact.metadata(Lists.newArrayList(new Metadata("info", "author", "patrick")));
    fact.metadata(new Metadata("info", "date", "2020-03-17"));

    Assert.assertEquals(2, fact.metadata_.size());
    Assert.assertEquals("info", fact.metadata_.get(0).type());
    Assert.assertEquals("author", fact.metadata_.get(0).key());
    Assert.assertEquals("patrick", fact.metadata_.get(0).value());
    Assert.assertEquals("info", fact.metadata_.get(1).type());
    Assert.assertEquals("date", fact.metadata_.get(1).key());
    Assert.assertEquals("2020-03-17", fact.metadata_.get(1).value());

    fact.provenance(
        new Provenance("STORAGE/ROOT/DATASET/DOC_ID", "ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z",
            "High", new Date(), new Date()));

    Assert.assertEquals(1, fact.provenances_.size());
    Assert.assertEquals("ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z",
        fact.provenances_.get(0).sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", fact.provenances_.get(0).sourceType_);
    Assert.assertEquals("High", fact.provenances_.get(0).sourceReliability_);
    Assert.assertNull(fact.provenances_.get(0).string_);
    Assert.assertNull(fact.provenances_.get(0).span_);
    Assert.assertNull(fact.provenances_.get(0).spanHash_);
    Assert.assertNull(fact.provenances_.get(0).startIndex_);
    Assert.assertNull(fact.provenances_.get(0).endIndex_);
    Assert.assertNull(fact.provenances_.get(0).page_);
  }
}
