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

            new Monstro( "Lobo", ItemDeUso.buscarItemPorNome("Garra De Fera"), Raca.buscarRacaPorId(14)),
            new Monstro( "Zumbi", ItemDeUso.buscarItemPorNome("Mãos vazias"), Raca.buscarRacaPorId(15)),
            new Monstro( "Goblin", ItemDeUso.buscarItemPorNome("Faca Rústica"), Raca.buscarRacaPorId(16)),

            new Monstro( "Aranha Gigante", ItemDeUso.buscarItemPorNome("Presas Venenosas"), Raca.buscarRacaPorId(18)),
            new Monstro( "Serpente", ItemDeUso.buscarItemPorNome("Presas venenosas"), Raca.buscarRacaPorId(20)),
            new Monstro( "Colosso esqueleto", ItemDeUso.buscarItemPorNome("Clava de ossos"), Raca.buscarRacaPorId(15)),

            new Monstro( "Espectro", ItemDeUso.buscarItemPorNome("Essência Sombria"), Raca.buscarRacaPorId(22)),
            new Monstro( "Ogro", ItemDeUso.buscarItemPorNome("Clava pesada"), Raca.buscarRacaPorId(17)),

            new Monstro( "Golem de Pedra", ItemDeUso.buscarItemPorNome("Punhos de Pedra"), Raca.buscarRacaPorId(21)),

            new Monstro( "Dragão Filhote", ItemDeUso.buscarItemPorNome("Garra de Dragão"), Raca.buscarRacaPorId(19))
    };


    public ListaMonstros () throws SQLException {};
    public Monstro monstroAleatorio(){
        int posicaoAletoria = aleatorio.nextInt(listaMonstros.length);
        return listaMonstros[posicaoAletoria];
    }
}


