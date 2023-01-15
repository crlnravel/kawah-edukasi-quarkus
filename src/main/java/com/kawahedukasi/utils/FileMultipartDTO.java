package com.kawahedukasi.utils;

import javax.ws.rs.FormParam;

public class FileMultipartDTO {

    @FormParam("file")
    public byte[] file;

}
