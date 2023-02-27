package com.beehyv.dsepvc.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

/* This creates test schema in the setup  */
public class SchemaCreator {

  public static final String TOKEN = "Test";
  public static void main ( String[] args) throws Exception{

      createSchema();
   
  }

  public static void createSchema() throws Exception{
    //Call APIs to create a QR Code for a given schema
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body = RequestBody.create(mediaType, "{\"name\":\"ResumeCertificate\",\"status\":\"DRAFT\",\"schema\":\"{\\\"$schema\\\":\\\"http://json-schema.org/draft-07/schema\\\",\\\"type\\\":\\\"object\\\",\\\"properties\\\":{\\\"ResumeCert\\\":{\\\"$ref\\\":\\\"#/definitions/ResumeCert\\\"}},\\\"required\\\":[\\\"ResumeCert\\\"],\\\"title\\\":\\\"ResumeCert\\\",\\\"definitions\\\":{\\\"ResumeCert\\\":{\\\"type\\\":\\\"object\\\",\\\"title\\\":\\\"\\\",\\\"required\\\":[\\\"issuanceDate\\\",\\\"issuer\\\",\\\"certificateId\\\",\\\"highestQualification\\\",\\\"passoutYear\\\"],\\\"properties\\\":{\\\"issuanceDate\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"Date certificate issued on\\\"},\\\"issuer\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"Name of the issuing authority\\\"},\\\"certificateId\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"The unique Certificate ID\\\"},\\\"validFrom\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"The date from which the credential is valid from\\\"},\\\"validTill\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"The date until which the credential is valid to\\\"},\\\"highestQualification\\\":{\\\"type\\\":\\\"string\\\",\\\"enum\\\":[\\\"\\\"],\\\"description\\\":\\\"\\\"},\\\"yearsOfExp\\\":{\\\"type\\\":\\\"integer\\\",\\\"description\\\":\\\"\\\"},\\\"name\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"\\\"},\\\"dOB\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"\\\"},\\\"university\\\":{\\\"type\\\":\\\"string\\\",\\\"description\\\":\\\"\\\"},\\\"passoutYear\\\":{\\\"type\\\":\\\"integer\\\",\\\"description\\\":\\\"\\\"}}}},\\\"_osConfig\\\":{\\\"uniqueIndexFields\\\":[\\\"certificateId\\\"],\\\"indexFields\\\":[\\\"certificateId\\\"],\\\"ownershipAttributes\\\":[],\\\"roles\\\":[],\\\"inviteRoles\\\":[],\\\"credentialTemplate\\\":{\\\"@context\\\":[\\\"https://www.w3.org/2018/credentials/v1\\\",\\\"http://vc-management-service:7655/vc-management/v1/context/c8f06e9f-9a07-46d6-9913-42b8a493848e\\\",\\\"https://www.w3.org/2018/credentials/v1\\\"],\\\"type\\\":[\\\"VerifiableCredential\\\",\\\"ProofOfResumeCertCredential\\\"],\\\"credentialSubject\\\":{\\\"type\\\":\\\"Person\\\"},\\\"issuer\\\":\\\"{{issuer}}\\\",\\\"issuanceDate\\\":\\\"{{issuanceDate}}\\\",\\\"evidence\\\":{\\\"type\\\":\\\"ResumeCert\\\",\\\"issuanceDate\\\":\\\"{{issuanceDate}}\\\",\\\"issuer\\\":\\\"{{issuer}}\\\",\\\"certificateId\\\":\\\"{{certificateId}}\\\",\\\"validFrom\\\":\\\"{{validFrom}}\\\",\\\"validTill\\\":\\\"{{validTill}}\\\",\\\"highestQualification\\\":\\\"{{highestQualification}}\\\",\\\"yearsOfExp\\\":\\\"{{yearsOfExp}}\\\",\\\"name\\\":\\\"{{name}}\\\",\\\"dOB\\\":\\\"{{dOB}}\\\",\\\"university\\\":\\\"{{university}}\\\",\\\"passoutYear\\\":\\\"{{passoutYear}}\\\"},\\\"nonTransferable\\\":\\\"true\\\"},\\\"certificateTemplates\\\":{\\\"default\\\":\\\"https://gist.githubusercontent.com/saiprakash-v/c5aa3d97de95806669b4ea26ec54bd55/raw/9f38b9c6d3e458e7facb658dbb3cb661af9664fb/templateWithOnlyQR.html\\\"}}}\"}");
    Request request = new Request.Builder()
  .url("https://dsepvc.beehyv.com/vc-management/v1/schema")
  .method("POST", body)
  .addHeader("Authorization", "Bearer " + TOKEN)
  .addHeader("Content-Type", "application/json")
  .build();
    Response response = client.newCall(request).execute();
    System.out.println(response.body().string());
    }

}
