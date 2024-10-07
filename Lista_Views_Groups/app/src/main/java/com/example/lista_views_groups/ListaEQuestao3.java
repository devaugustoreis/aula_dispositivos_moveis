package com.example.lista_views_groups;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaEQuestao3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // 1 - Explique o que é um ViewGroup?
    // R: Para entender uma ViewGroup, convém primeiro entender o conceito de uma View. Uma view
    // representa um componente da interface, como por exemplo, um botão ou uma caixa de texto.
    // Logo, como o próprio nome sugere, uma ViewGroup trata-se de um container que pode conter
    // várias Views (ou mesmo várias outras ViewGroups). Uma ViewGroup é uma subclasse da classe
    // View e define também como os elementos internos ficarão organizados, existindo várias
    // ViewGroups com comportamentos distintos, tais como LinearLayout, ConstraintLayout, entre outros.

    // 2 - O que é a classe LayoutParams e qual a relação dela com ViewGroups?
    // R: Os LayoutParams definem como um elemento será posicionado dentro de uma ViewGroup, estipulando
    // medidas de posição e dimensão (altura, largura). Toda View possui um conjunto de LayoutParams,
    // sendo que diferente tipos de ViewsGroups (tais como LinearLayout ou ConstraintLayout), possuem
    // parâmetros e comportamentos diferentes de como determinar a posição e dimensão dos elementos filhos.

    // 4 - Descreva as propriedades wrap_content e match_parent, cite exemplo se julgar necessário.
    // WRAP_CONTENT -> Ajusta a largura/altura da View de acordo com seu conteúdo interno.
    // MATCH_PARENT -> Ajusta a larguta/altura da View para ocupar todoo o espaço disponível no container pai.

    // 5 - Descreva a propriedade gravity no LinearLayout.
    // R: A propriedade gravity define a posição de todas as Views filhas em relação ao container pai.
    // Por exemplo, as Views filhas podem ser alinhas no lado direito, esquerdo ou centralizadas (no eixo
    // horizontal) ou no topo, centro ou parte inferior (no eixo vertical).

    // 6 - No layout descrito no XML abaixo era esperado que os textos definidos em nos TextViews
    // estivessem um em cada linha, encontre a solução e descreva.
    // R: Para garantir que os textos ficarão um em cada linha, é possível adicionar a propriedade
    // orientation="vertical", que fará com que os elementos filhos sejam empilhados no eixo vertical
    // de um LinearLayout.
}