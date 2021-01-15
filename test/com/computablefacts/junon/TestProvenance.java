package com.computablefacts.junon;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TestProvenance {

  @Test
  public void testHashcodeAndEquals() {
    EqualsVerifier.forClass(Provenance.class).verify();
  }
}
