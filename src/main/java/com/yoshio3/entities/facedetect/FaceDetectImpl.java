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

import com.yoshio3.utils.MyObjectMapperProvider;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author Yoshio Terada
 */
public class FaceDetectImpl {

    /*
        対応ォーマット： JPEG, PNG, GIF(最初のフレーム), BMP
        画像サイズ： 4MB 以下
        検知可能な顔のサイズ：36x36 〜 4096x4096
        一画像辺り検知可能な人数：64 名
        指定可能な属性オプション(まだ実験的不正確)：
            age, gender, headPose, smile and facialHair, and glasses
            HeadPose の pitch 値は 0 としてリザーブ
    */
    
    public List<FaceDetectResponseJSONBody> getDetectedPerson(String pictURI, String subsctiption){
        String baseURI = "https://api.projectoxford.ai/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=age,gender";
        
        Client client = ClientBuilder.newBuilder()
            .register(MyObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();

        WebTarget target = client.target(baseURI);
        FaceDetectRequestJSONBody entity = new FaceDetectRequestJSONBody();
        entity.setUrl(pictURI);
        
        Response response = target
                .request(MediaType.APPLICATION_JSON)
                .header("Ocp-Apim-Subscription-Key", subsctiption)
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        
        FaceDetectResponseJSONBody[] persons = response.readEntity(FaceDetectResponseJSONBody[].class);
        List<FaceDetectResponseJSONBody> list = Arrays.asList(persons);
        return list;
    }
    
    public void printDetectedPerson(String pictURI, String subsctiption) {
        List<FaceDetectResponseJSONBody> list = getDetectedPerson(pictURI,subsctiption);
        list.stream().forEach(person -> 
            System.out.println(
                            person.getFaceId() + "\t"  + 
                            person.getFaceRectangle() + "\t" +  
                            person.getFaceAttributes()));
    }    
}
