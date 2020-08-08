package com.spring.ex.web.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

    @Getter
    @RequiredArgsConstructor
   public class HelloResponseDto{
        private final String name;
        private final int amount;

        public HelloResponseDto(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }


        @Override
        public String toString() {
            return "HelloResponseDto{" +
                    "name='" + name + '\'' +
                    ", amount=" + amount +
                    '}';
        }

        public int getAmount() {
            return amount;
        }

        public String getName() {
            return name;
        }
    }
