package org.example.hotelmanagementsystem.model.reqDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DownloadOrderDto {
    LocalDate from;
    LocalDate to;
}
