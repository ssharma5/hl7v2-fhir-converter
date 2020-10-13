/*
 * (C) Copyright IBM Corp. 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.whi.hl7.expression;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.ibm.whi.api.EvaluationResult;
import com.ibm.whi.api.InputDataExtractor;
import com.ibm.whi.api.Variable;
import com.ibm.whi.core.expression.EmptyEvaluationResult;
import com.ibm.whi.hl7.resource.deserializer.TemplateFieldNames;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JELXExpression extends AbstractExpression {
  private static final Logger LOGGER = LoggerFactory.getLogger(JELXExpression.class);

  private String evaluate;



  @JsonCreator
  public JELXExpression(@JsonProperty(TemplateFieldNames.TYPE) String type,
      @JsonProperty(TemplateFieldNames.DEFAULT_VALUE) String defaultValue,
      @JsonProperty(TemplateFieldNames.REQUIRED) boolean required,
      @JsonProperty(TemplateFieldNames.SPEC) String specs,
      @JsonProperty(TemplateFieldNames.EVALUATE) String evaluate,
      @JsonProperty(TemplateFieldNames.VARIABLES) Map<String, String> variables,
      @JsonProperty(TemplateFieldNames.CONDITION) String condition,
      @JsonProperty(TemplateFieldNames.CONSTANTS) Map<String, String> constants,
      @JsonProperty(TemplateFieldNames.USE_GROUP) boolean useGroup) {
    super(type, defaultValue, required, specs, variables, condition, constants, useGroup);
    Preconditions.checkArgument(StringUtils.isNotBlank(evaluate), "evaluate file cannot be blank");

    this.evaluate = evaluate;


  }


  public JELXExpression(String evaluate, Map<String, String> variables) {
    this("String", null, false, null, evaluate, variables, null, null, false);


  }


  @Override
  public EvaluationResult evaluateExpression(InputDataExtractor dataSource,
      Map<String, EvaluationResult> contextValues, EvaluationResult baseValue) {
    Map<String, EvaluationResult> localContextValues = new HashMap<>(contextValues);
    for (Variable v : this.getVariables()) {
      if (!localContextValues.containsKey(v.getVariableName())) {
        localContextValues.put(v.getVariableName(), new EmptyEvaluationResult());
      }
    }
    return dataSource.evaluateJexlExpression(this.evaluate, contextValues);
  }


  @Override
  public String toString() {
    ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
    return new ToStringBuilder(this)
        .append(TemplateFieldNames.TYPE, this.getClass().getSimpleName())
        .append(TemplateFieldNames.SPEC, this.getspecs()).append("isMultiple", this.isMultiple())
        .append(TemplateFieldNames.VARIABLES, this.getVariables())
        .append(TemplateFieldNames.EVALUATE, this.evaluate).build();
  }


}
