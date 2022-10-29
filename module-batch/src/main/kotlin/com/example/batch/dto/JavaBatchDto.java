package com.example.batch.dto;

public class JavaBatchDto {

    public static class CsvMember {

        String id;
        String email;
        String password;
        String role;
        String createAt;
        String updateAt;
        String deleteAt;
        String orderNo;
        String postTitles;


        public CustomMember toCustomMember() {

            return new CustomMember(
                    this.id,
                    this.email,
                    this.password,
                    this.role,
                    this.createAt,
                    this.updateAt,
                    this.deleteAt,
                    Long.valueOf(this.orderNo),
                    this.postTitles.replaceAll("#", ",")
            );
        }

    }

}
