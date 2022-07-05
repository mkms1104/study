package com.example.awssdk.kinesis;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.Record;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

public class Kinesis {

    @Test
    @DisplayName("키네시스 스트림 예시")
    public void kinesisStream() {
        try (KinesisClient client = KinesisClient.builder()
                                                .region(Region.AP_NORTHEAST_2)
                                                .credentialsProvider(() -> AwsBasicCredentials.create("AKIA3GARC7SWK2PVIZXM", "DRyRSpkFZK09cr0hoZDgACiyX0/IUv41ThJGPu/x"))
                                                .build()
        ) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", "mskim");

            PutRecordRequest putRecordRequest = PutRecordRequest.builder()
                                                                .data(SdkBytes.fromByteArray(jsonObject.toString().getBytes())).streamName("...")
                                                                .partitionKey("1")
                                                                .build();
            client.putRecord(putRecordRequest);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void kinesisFirehose () {
        try (FirehoseClient client = FirehoseClient.builder()
                                                    .region(Region.AP_NORTHEAST_2)
                                                    .credentialsProvider(() -> AwsBasicCredentials.create("AKIA3GARC7SWK2PVIZXM", "DRyRSpkFZK09cr0hoZDgACiyX0/IUv41ThJGPu/x"))
                                                    .build()) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", "mskim");
            jsonObject.addProperty("age", 30);

            var putRecordRequest = software.amazon.awssdk.services.firehose.model.PutRecordRequest.builder()
                                                                                                                    .deliveryStreamName("PUT-OPS-7RONq")
                                                                                                                    .record(Record.builder().data(SdkBytes.fromByteArray(jsonObject.toString().getBytes())).build())
                                                                                                                    .build();

            client.putRecord(putRecordRequest);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

