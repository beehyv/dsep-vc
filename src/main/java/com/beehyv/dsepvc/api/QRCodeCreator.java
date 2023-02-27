package com.beehyv.dsepvc.api;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import com.beehyv.dsepvc.model.CertifyOutput;
import com.beehyv.dsepvc.model.TransactionOutput;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Request;

/**
 * These 3 creates QR code and gets the file
 */
public class QRCodeCreator {

  public static final String TOKEN = "test";
    
  public static void main(String[] args) throws Exception{
    File qrCode = getQRCode("846545505393");
   
  }
    public static File getCert() throws Exception{
        String transactionId  = certify();
        String certId = getTransaction(transactionId);
        File qrCode = getQRCode(transactionId);
        return qrCode;
    }



    static String  certify() throws Exception{
    OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("application/json");
RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\":\"swathiG\",\r\n \"dob\":\"1991-11-29\",\r\n \"registrationId\":\"123456\",\r\n \"gender\":\"female\",\"registrationCouncil\":\"Karnataka\",\"latestQualification\":\"quali\",\r\n  \"university\":\"Testuniversity\",\r\n   \"degreeYear\":\"2019\", \"systemOfMedicine\":\"testsystem\", \"registrationDate\":\"2021-09-09\", \"registrationExpiry\":\"2022-09-09\", \"issuer\":\"http://www.india.gov.in\", \"issuanceDate\":\"2021-09-17T22:00:00.000Z\"}");
Request request = new Request.Builder()
  .url("http://jobsdsep.beehyv.com/vc-certification/v1/certify/ResumeCert")
  .method("POST", body)
  .addHeader("Content-Type", "application/json")
  .addHeader("Authorization", "Bearer " + TOKEN)
  .build();
Response response = client.newCall(request).execute();
String resp = response.body().string();
ObjectMapper mapper = new ObjectMapper();
CertifyOutput cert = mapper.readValue(resp, CertifyOutput.class);
return cert.getTransactionId();

//TODO reso
}

static String  getTransaction(String transId) throws Exception{
    OkHttpClient client = new OkHttpClient().newBuilder()
    .build();
  MediaType mediaType = MediaType.parse("text/plain");
  //RequestBody body = RequestBody.create(mediaType, "");
  Request request = new Request.Builder()
    .url("http://dsepjobs.beehyv.com/vc-management/v1/transaction/" + transId)
    .addHeader("template-key", "html")
    .addHeader("Accept", "application/pdf")
    .addHeader("Authorization", "Bearer " + TOKEN)
    .build();
  Response response = client.newCall(request).execute();
  String output = response.body().string();
   ObjectMapper mapper = new ObjectMapper();
TransactionOutput cert = mapper.readValue(output, TransactionOutput.class);
String certId = cert.getCertificateId();
return certId;

}

static File getQRCode(String transactionId) throws Exception{
    OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
Request request = new Request.Builder()
  .url("https://dsepvc.beehyv.com/vc-certification/v1/certificate/HealthProfessional84/" + transactionId)
  .addHeader("template-key", "svg1")
  .addHeader("Accept", "image/svg+xml")
  .addHeader("Authorization", "Bearer " + TOKEN)
  .build();
Response response = client.newCall(request).execute();
double length = Double.parseDouble(Objects.requireNonNull(response.header("Content-Length", "1")));
FileOutputStream fos  = new FileOutputStream("D:\\work\\skillathon\\qrcode.svg");
OutputStream writer = new ObjectOutputStream(fos);
write(response.body().byteStream(), writer);
writer.close();


  return null;
}

public static long write(InputStream inputStream, OutputStream outputStream) throws IOException {
  try (BufferedInputStream input = new BufferedInputStream(inputStream)) {
      byte[] dataBuffer = new byte[10];
      int readBytes;
      long totalBytes = 0;
      while ((readBytes = input.read(dataBuffer)) != -1) {
          totalBytes += readBytes;
          outputStream.write(dataBuffer, 0, readBytes);
      }
      return totalBytes;
  }
}
}
