package com.example.demo.application.utils;

public class PaginationPointCalculator {

  public static int getStartPointLimit(int page, int limit) {
    return (page - 1) * limit;
  }

}
