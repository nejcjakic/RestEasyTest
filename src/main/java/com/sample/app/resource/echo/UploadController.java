package com.sample.app.resource.echo;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/uploadresteasy")
@Component
public class UploadController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response findCards(MultipartFormDataInput input) {

    try {
      File localFile = new File("/Users/nejcj/tmp/test.jpg");

      byte[] localBytes = Files.readAllBytes(localFile.toPath());
      LOGGER.info(Integer.toString(localBytes.length));
    } catch (IOException e) {
      LOGGER.error("LOCAL FAILED", e);
    }

    try {
      byte[] bytes = input.getFormDataPart("file", byte[].class, null);
      LOGGER.info(Integer.toString(bytes.length));

    } catch (IOException e) {
      LOGGER.error("FAILED", e);
      return Response.status(Status.BAD_REQUEST).build();
    }

    return Response.ok().build();
  }
}
