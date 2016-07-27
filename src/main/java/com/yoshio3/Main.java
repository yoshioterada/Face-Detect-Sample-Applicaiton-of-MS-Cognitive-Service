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

import com.yoshio3.entities.emotion.EmotionImpl;
import com.yoshio3.entities.emotion.EmotionResponseJSONBody;
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

    public static void main(String... args) {
        String faceDetectSubscripotionID = "********************************";
        String emotionSubscriptionID = "********************************";

        FaceDetectImpl facedetect = new FaceDetectImpl();
        List<FaceDetectResponseJSONBody> persons1 = facedetect.getDetectedPerson("https://c2.staticflickr.com/8/7380/26803752033_b378bcde4d.jpg", faceDetectSubscripotionID);
        List<FaceDetectResponseJSONBody> persons2 = facedetect.getDetectedPerson("https://c1.staticflickr.com/1/606/22500593665_540df42382.jpg", faceDetectSubscripotionID);
        //List<FaceDetectResponseJSONBody> persons2 = facedetect.getDetectedPerson("https://c1.staticflickr.com/1/689/22511694501_b0cb19dfca.jpg", faceDetectSubscripotionID);

        persons1.stream().forEach(psn1 -> {
            String faceId1 = psn1.getFaceId();
            Optional<FaceDetectResponseJSONBody> findPerson
                    = persons2.stream().filter(psn2 -> {
                        FaceVerifyImpl faceVerify = new FaceVerifyImpl();
                        return faceVerify.isEqualofTwoFaces(faceId1, psn2.getFaceId(), faceDetectSubscripotionID);
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

        EmotionImpl emotion = new EmotionImpl();
        List<EmotionResponseJSONBody> emPersons1 = emotion.getEmotionalPerson("http://comonetsapo.com/wp-content/uploads/2013/08/2013y08m26d_105217892.jpg", emotionSubscriptionID);        
        emPersons1.stream().forEach(emp -> System.out.println(emp.getFaceRectangle() + "\t" + emp.getScores()));
    }
}
