package com.srm.student.request;

import java.util.List;

//StudentRequestData  class to capture the incoming request body
public class StudentRequestData {
    
        private List<String> data;
        private String fileB64;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }

        public String getFileB64() {
            return fileB64;
        }

        public void setFileB64(String fileB64) {
            this.fileB64 = fileB64;
        }
    }

