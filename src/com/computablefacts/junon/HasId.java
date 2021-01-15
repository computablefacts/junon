package com.computablefacts.junon;

import java.time.Instant;

import com.computablefacts.nona.Generated;
import com.computablefacts.nona.helpers.RandomString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HasId {

  @JsonProperty("external_id")
  public final String externalId_;
  @JsonIgnore
  public int id_;

  public HasId() {
    externalId_ = new RandomString(5).nextString() + '|' + Instant.now().toString();
  }
}
