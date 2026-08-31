
# MATRIX FX - pgmatheus-code ![Main menu](src/img/icon.ico)

---

## Origem

Esse é um **software com propósito educacional** que foi escrito durante o ano de
2018 e precisou passar por revisão quando foi executado novamente em 2026. Foi escrito
como parte de uma disciplina de Lógica de Programação e Algoritmos, do curso de Análise
e desenvolvimento de sistemas, do IRFS-BG.

![Main menu](src/img/menu_principal_exemplo.png)

Nele, você pode fazer algumas operações básicas com matrizes, utilizando uma interface
escrita para JavaFX. O charme do programa é que ele foi construído pensando em matrizes
com valores em números romanos. 

![Main menu](src/img/teste_com_matrix_calc.png)

A discilina na época não exigia que as telas fossem interfaces gráficas, nem mesmo que
fossem em JavaFX, ou ainda que o input fosse em números romanos. Entretanto, sendo um
entusiasta de JFX na época, aceitei o desafio e dobrei a aposta.

---

## Requerimentos atuais

Hoje o programa está habilitado para as seguintes versões:
- JDK 26.0.2.1
- JRE 26.0.2
- openJFX 26.0.2

---

## Revisão (2018-2026)

Para conseguir rodar novamente, precisei baixar e instalar as versões dos softwares
requeridos acima, além de adicionar os JARs da openJFX no projeto:

- Clique direito no projeto, Properties > Java Build Path > Add External JARs >
(adicionar todas os JARs da pasta lib do openJFX.)

![Main menu](src/img/add_external_jars.png)

E finalmente acrescentar o seguinte comando:

--module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics

via: Run > Run Configurations > Java Appication > Main > Aba "Arguments" > VM Arguments.

![Main menu](src/img/run_config.png)

**As instruções acima servem para a IDE Eclipse. Além disso foi instalado e(fx)clipse 3.8.0 via marketplace.

---

Made with 💻 and 🎮 by [**pgmatheus-code**](https://github.com/pgmatheus-code)
