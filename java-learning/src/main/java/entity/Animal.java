package entity;

import lombok.*;

import java.util.Objects;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-24
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Animal {
    private String name;

    private Integer age;


    public int countLegs() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) && Objects.equals(age, animal.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
