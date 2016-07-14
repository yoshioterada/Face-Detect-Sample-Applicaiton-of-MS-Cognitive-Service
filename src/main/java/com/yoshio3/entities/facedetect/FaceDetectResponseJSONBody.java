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
package com.yoshio3.entities.facedetect;

import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceDetectResponseJSONBody {
    private String faceId;
    private Map<String, Object> faceRectangle;
    private Map<String, Object> faceAttributes;

    /**
     * @return the faceId
     */
    public String getFaceId() {
        return faceId;
    }

    /**
     * @param faceId the faceId to set
     */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * @return the faceRectangle
     */
    public Map<String, Object> getFaceRectangle() {
        return faceRectangle;
    }

    /**
     * @param faceRectangle the faceRectangle to set
     */
    public void setFaceRectangle(Map<String, Object> faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    /**
     * @return the faceAttributes
     */
    public Map<String, Object> getFaceAttributes() {
        return faceAttributes;
    }

    /**
     * @param faceAttributes the faceAttributes to set
     */
    public void setFaceAttributes(Map<String, Object> faceAttributes) {
        this.faceAttributes = faceAttributes;
    }
    
}
