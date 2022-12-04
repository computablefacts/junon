package com.computablefacts.junon;

import java.util.Date;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Test;

public class TestProvenance {

  @Test
  public void testHashcodeAndEquals() {
    EqualsVerifier.forClass(Provenance.class).verify();
  }

  @Test
  public void testConstructorWith5Params() {

    Provenance provenance = new Provenance("STORAGE/ROOT/DATASET/DOC_ID",
        "ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", "High", new Date(), new Date());

    Assert.assertEquals("ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", provenance.sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", provenance.sourceType_);
    Assert.assertEquals("High", provenance.sourceReliability_);
    Assert.assertNull(provenance.string_);
    Assert.assertNull(provenance.span_);
    Assert.assertNull(provenance.spanHash_);
    Assert.assertNull(provenance.startIndex_);
    Assert.assertNull(provenance.endIndex_);
    Assert.assertTrue(provenance.extractionDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertTrue(provenance.modificationDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertNull(provenance.page_);
  }

  @Test
  public void testConstructorWith7Params() {

    Provenance provenance = new Provenance("STORAGE/ROOT/DATASET/DOC_ID",
        "ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", "High", new Date(), new Date(), 1,
        "A single page.");

    Assert.assertEquals("ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", provenance.sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", provenance.sourceType_);
    Assert.assertEquals("High", provenance.sourceReliability_);
    Assert.assertEquals("A single page.", provenance.string_);
    Assert.assertNull(provenance.span_);
    Assert.assertNull(provenance.spanHash_);
    Assert.assertNull(provenance.startIndex_);
    Assert.assertNull(provenance.endIndex_);
    Assert.assertTrue(provenance.extractionDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertTrue(provenance.modificationDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertEquals(1, (int) provenance.page_);
  }

  @Test
  public void testConstructorWith10Params() {

    Provenance provenance = new Provenance("STORAGE/ROOT/DATASET/DOC_ID",
        "ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", "High", new Date(), new Date(), 1,
        "A single page.", "single", 0, "single".length());

    Assert.assertEquals("ACCUMULO/client_prod/example/gcqcl|2021-01-27T23:23:45.006Z", provenance.sourceStore_);
    Assert.assertEquals("STORAGE/ROOT/DATASET/DOC_ID", provenance.sourceType_);
    Assert.assertEquals("High", provenance.sourceReliability_);
    Assert.assertEquals("A single page.", provenance.string_);
    Assert.assertEquals("single", provenance.span_);
    Assert.assertEquals("c9e449e2873d5b8796c2794f198d6cef", provenance.spanHash_);
    Assert.assertEquals(0, (int) provenance.startIndex_);
    Assert.assertEquals("single".length(), (int) provenance.endIndex_);
    Assert.assertTrue(provenance.extractionDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertTrue(provenance.modificationDate_.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*Z$"));
    Assert.assertEquals(1, (int) provenance.page_);
  }
}
