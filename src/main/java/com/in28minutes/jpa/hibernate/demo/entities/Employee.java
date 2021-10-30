package com.in28minutes.jpa.hibernate.demo.entities;

import javax.persistence.*;

 @Entity
// One table Inheritance
// Uma tabela para todos as classes filhas diferenciadas pela coluna(@DiscriminatorColumn)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "EmployeeType")
// Table per class
// Cria uma tabela para cada classe filha
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// JOINED
// Cria uma tabela "mãe" com dados da classe mãe e duas tabelas com dados da classe filha e chave estrangera para a tabela mãe
 @Inheritance(strategy = InheritanceType.JOINED)
// MAPPED SUPER CLASS
// Não pode ser entity e mapped super class ao mesmo tempo
// Entity Manager deve ser usado apenas nas subclasses, pois são entitys
// Cria duas tabelas separadas, sem relacionamento entre as duas
// @MappedSuperclass
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
