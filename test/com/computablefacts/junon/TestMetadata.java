package com.computablefacts.junon;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TestMetadata {

  @Test
  public void testHashcodeAndEquals() {
    EqualsVerifier.forClass(Metadata.class).verify();
  }
}
