/*
 * (C) Copyright IBM Corp. 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.whi.api;

import java.util.Map;

/**
 * Defines Data source specific data extraction String Example: for HL7 data, specification defines
 * segment, field, component and subcomponent names/identifiers that can be used for extracting
 * data.
 * 
 *
 * @author pbhallam
 */

public interface Specification {


  Class<? extends InputDataExtractor> getSourceInputDataClass();


  /**
   * Extract the single value for the specifications.
   * 
   * 
   * @param dataSource {@link InputData)
   * @param contextValues {@link Map<String, EvaluationResult> }
   * @return {@link EvaluationResult}
   */
  EvaluationResult extractValueForSpec(InputDataExtractor dataSource,
      Map<String, EvaluationResult> contextValues);

  /**
   * Extract the multiple values for the specifications.
   * 
   *
   * @param dataSource {@link InputData)
   * @param contextValues {@link Map<String, EvaluationResult> }
   * @return {@link EvaluationResult}
   */
  EvaluationResult extractMultipleValuesForSpec(InputDataExtractor dataSource,
      Map<String, EvaluationResult> contextValues);

}
