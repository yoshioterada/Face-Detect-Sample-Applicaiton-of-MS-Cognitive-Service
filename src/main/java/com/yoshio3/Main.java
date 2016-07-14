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

package com.yoshio3;

import com.yoshio3.entities.facedetect.FaceDetectResponseJSONBody;
import com.yoshio3.entities.facedetect.FaceDetectImpl;
import com.yoshio3.entities.verify.FaceVerifyImpl;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Yoshio Terada
 */
public class Main {

    public static void main(String[] args) {
        String subscripotionID = "*********************************************";

        FaceDetectImpl facedetect = new FaceDetectImpl();

        List<FaceDetectResponseJSONBody> persons1 = facedetect.getDetectedPerson("https://c2.staticflickr.com/8/7380/26803752033_b378bcde4d.jpg", subscripotionID);
        //List<FaceDetectResponseJSONBody> persons2 = facedetect.getDetectedPerson("https://c1.staticflickr.com/1/606/22500593665_540df42382.jpg", subscripotionID);
        List<FaceDetectResponseJSONBody> persons2 = facedetect.getDetectedPerson("https://c1.staticflickr.com/1/689/22511694501_b0cb19dfca.jpg", subscripotionID);

        persons1.stream().forEach(psn1 -> {
            String faceId1 = psn1.getFaceId();
            Optional<FaceDetectResponseJSONBody> findPerson
                    = persons2.stream().filter(psn2 -> {
                        FaceVerifyImpl faceVerify = new FaceVerifyImpl();
                        return faceVerify.isEqualofTwoFaces(faceId1, psn2.getFaceId(), subscripotionID);
                    }).findFirst();
            findPerson.ifPresent(findPerson2 -> System.out.println(faceId1 + "\t" + findPerson2.getFaceId() + "は同一人物の可能性大"));
        });

        /* 
        if you write the loop sequence until using Java SE 7, follwing.  
        for(FaceDetectResponseJSONBody person : persons1){
            String faceId1 = person.getFaceId();
            for(FaceDetectResponseJSONBody psn2 : persons2){
                String faceId2 = psn2.getFaceId();
                FaceVerifyImpl faceVerify = new FaceVerifyImpl();
                if(faceVerify.isEqualofTwoFaces(faceId1, faceId2, subscripotionID)){
                    System.out.println(faceId1 + "\t" + faceId2 + "は同一人物の可能性大");
                }
            }
        }*/
    }
}
