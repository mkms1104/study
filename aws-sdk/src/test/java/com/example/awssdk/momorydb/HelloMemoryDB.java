package com.example.awssdk.momorydb;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.awscore.AwsRequest;
import software.amazon.awssdk.core.SdkRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.memorydb.MemoryDbClient;
import software.amazon.awssdk.services.memorydb.model.*;

import java.util.List;
import java.awt.*;
import java.net.URI;
import java.util.Optional;

public class HelloMemoryDB {

    @Test
    public void abc() {
        try (MemoryDbClient client = MemoryDbClient.builder()
                                                    .region(Region.AP_NORTHEAST_2)
                                                    .credentialsProvider(() -> AwsBasicCredentials.create("AKIA3GARC7SWK2PVIZXM", "DRyRSpkFZK09cr0hoZDgACiyX0/IUv41ThJGPu/x"))
                                                    .build()
        ) {
            Optional<List> admin = client.describeUsers(DescribeUsersRequest.builder().build()).getValueForField("Users", List.class);
            System.out.println(admin.get()); // 뭔가 찍히긴 한다.

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("오류몰랑");
        } finally {

        }
    }
}
