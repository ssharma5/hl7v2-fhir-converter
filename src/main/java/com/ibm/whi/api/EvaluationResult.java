/*
 * (C) Copyright IBM Corp. 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.whi.api;

import java.util.List;


/**
 * Represents value returned after the expression is evaluated.
 * 
 * @param <T> the parameter value generated after the evaluation or an resource or an expression
 *
 * @author pbhallam
 */
public interface EvaluationResult {

  /**
   * Value generated after the evaluation or an resource or an expression
   * 
   * @param <V>
   * 
   * @return V
   */
  <V> V getValue();

  /**
   * Get name/identifier for the return value;
   * 
   * @return String
   */
  String getIdentifier();

  /**
   * Class type of the value
   * 
   * @return
   */
  Class<?> getValueType();

  /**
   * If the value is empty return true.
   * 
   * @return
   */
  boolean isEmpty();

  /**
   * Additional resources generated during evaluation or an resource or an expression
   * 
   * @return List of {@link ResourceValue}
   */
  List<ResourceValue> getAdditionalResources();

}
