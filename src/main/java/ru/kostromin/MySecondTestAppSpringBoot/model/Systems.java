package ru.kostromin.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Systems {

  ERP("Enterprise Resource Planning"),
  CRM("Customer Relationship Management"),
  WMS("Warehouse Management System");

  private final String name;

  Systems(String name) {
    this.name = name;
  }
}
