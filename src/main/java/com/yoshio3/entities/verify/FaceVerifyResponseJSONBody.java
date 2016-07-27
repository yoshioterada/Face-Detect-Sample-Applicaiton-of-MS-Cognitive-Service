/*
* Copyright 2016 Yoshio Terada
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.yoshio3.entities.verify;

/**
 *
 * @author Yoshio Terada
 */
public class FaceVerifyResponseJSONBody {
    private String isIdentical; 
    private Double confidence;

    /**
     * @return the isIdentical
     */
    public String getIsIdentical() {
        return isIdentical;
    }

    /**
     * @param isIdentical the isIdentical to set
     */
    public void setIsIdentical(String isIdentical) {
        this.isIdentical = isIdentical;
    }

    /**
     * @return the confidence
     */
    public Double getConfidence() {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "FaceVerifyResponseJSONBody{" + "isIdentical=" + isIdentical + ", confidence=" + confidence + '}';
    }

}
