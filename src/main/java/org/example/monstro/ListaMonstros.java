package org.example.monstro;

import lombok.Getter;
import org.example.atributos.ItemDeUso;
import org.example.atributos.ListaHabilidades;
import org.example.atributos.Raca;
import org.example.monstro.Monstro;
import org.example.personagem.Personagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ListaMonstros {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemarpg";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";
    Random aleatorio = new Random();
    @Getter
    private Monstro[] listaMonstros = {

            new Monstro(1, 2, 3, 3, 1, 3, "Lobo", ItemDeUso.buscarItemPorNome("Garra De Fera"), Raca.buscarRacaPorId(14)),
            new Monstro(1, 3, 2, 4, 1, 2, "Zumbi", ItemDeUso.buscarItemPorNome("Mãos vazias"), Raca.buscarRacaPorId(15)),
            new Monstro(1, 2, 3, 2, 3, 2, "Goblin", ItemDeUso.buscarItemPorNome("Faca Rústica"), Raca.buscarRacaPorId(16)),

            new Monstro(2, 2, 5, 2, 2, 4, "Aranha Gigante", ItemDeUso.buscarItemPorNome("Presas Venenosas"), Raca.buscarRacaPorId(18)),
            new Monstro(2, 4, 4, 2, 3, 2, "Serpente", ItemDeUso.buscarItemPorNome("Presas venenosas"), Raca.buscarRacaPorId(20)),
            new Monstro(2, 5, 3, 3, 2, 2, "Colosso esqueleto", ItemDeUso.buscarItemPorNome("Clava de ossos"), Raca.buscarRacaPorId(15)),

            new Monstro(3, 2, 5, 2, 3, 5, "Espectro", ItemDeUso.buscarItemPorNome("Essência Sombria"), Raca.buscarRacaPorId(22)),
            new Monstro(3, 6, 3, 4, 1, 3, "Ogro", ItemDeUso.buscarItemPorNome("Clava pesada"), Raca.buscarRacaPorId(17)),

            new Monstro(4, 7, 1, 7, 1, 3, "Golem de Pedra", ItemDeUso.buscarItemPorNome("Punhos de Pedra"), Raca.buscarRacaPorId(21)),

            new Monstro(5, 4, 5, 4, 4, 4, "Dragão Filhote", ItemDeUso.buscarItemPorNome("Garra de Dragão"), Raca.buscarRacaPorId(19))
    };


    public ListaMonstros (){};
    public Monstro monstroAleatorio(){
        int posicaoAletoria = aleatorio.nextInt(listaMonstros.length);
        return listaMonstros[posicaoAletoria];
    }
}


