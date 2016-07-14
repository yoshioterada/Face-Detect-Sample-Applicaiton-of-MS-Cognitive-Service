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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yoshio Terada
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FaceVerifyRequestJSONBody {
    private String faceId1;
    private String faceId2;

    /**
     * @return the faceId1
     */
    public String getFaceId1() {
        return faceId1;
    }

    /**
     * @param faceId1 the faceId1 to set
     */
    public void setFaceId1(String faceId1) {
        this.faceId1 = faceId1;
    }

    /**
     * @return the faceId2
     */
    public String getFaceId2() {
        return faceId2;
    }

    /**
     * @param faceId2 the faceId2 to set
     */
    public void setFaceId2(String faceId2) {
        this.faceId2 = faceId2;
    }
}
