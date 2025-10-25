# 🧩 Estruturas de Dados com Visualização Gráfica em Java  
### 🔹 Listas Simples, Duplamente Encadeadas e Circulares com GUI Interativa  

> Projeto acadêmico desenvolvido por **Caio Palácio** para demonstrar o funcionamento das principais estruturas de listas encadeadas em Java — com uma interface gráfica animada que permite visualizar inserções, remoções e buscas em tempo real.

 Link : https://youtu.be/mJQYW2-3xow
---

## 🚀 Objetivo
O projeto tem como objetivo **explorar e visualizar o comportamento de listas encadeadas**, mostrando suas diferenças estruturais e funcionais de forma prática e didática.  
A interface Swing foi projetada para **animar as operações de inserção, remoção e busca**, facilitando o entendimento dos encadeamentos e ponteiros.

---

## 🧠 Estruturas Implementadas

### 🔸 Lista Simplesmente Encadeada (`ListIncadeSimple`)
- Cada nó aponta apenas para o **próximo**.
- Ideal para percorrer dados em **uma única direção**.
- Operações:  
  - Inserir elemento  
  - Remover elemento  
  - Buscar valor  
  - Exibir elementos

### 🔹 Lista Duplamente Encadeada (`ListDoubleIncluded`)
- Cada nó contém ponteiros para o **anterior e o próximo**.
- Permite percorrer em **duas direções**.
- Operações:  
  - Inserção com atualização dos dois ponteiros  
  - Remoção segura em qualquer posição  
  - Busca direta  
  - Exibição bidirecional

### 🔁 Lista Circular (`ListCircular`)
- O **último nó aponta de volta para o primeiro**, criando um ciclo.
- Ideal para estruturas que precisam ser percorridas continuamente.
- Operações:  
  - Inserção circular  
  - Remoção mantendo o ciclo  
  - Busca circular  
  - Impressão contínua até retornar ao início

---

## 🖼️ Interface Gráfica (Swing)

A GUI foi implementada com **Java Swing**, permitindo:
- Escolher o tipo de lista: `Simples`, `Dupla`, `Circular`  
- Inserir, remover ou buscar valores dinamicamente  
- Visualizar as operações com **animações suaves e coloridas**

### 💡 Destaques Visuais:
- Nós representados como **blocos arredondados coloridos**  
- **Setas dinâmicas** que mudam conforme o tipo de lista (→, ↔, ↩)  
- **Efeitos de fade e highlight** durante as buscas  
- **Feedback visual** em tempo real

---

## 🧱 Estrutura de Pastas
📦 src/
┣ 📂 app/
┃ ┣ 📂 gui/                
┃ ┃ ┣ 📜 ListGUI.java
┃ ┃ ┗ 📜 ListVisualizerGUI.java
┃ ┣ 📂 struct/             
┃ ┃ ┣ 📂 simple/
┃ ┃ ┃ ┣ 📜 ListIncadeSimple.java    
┃ ┃ ┃ ┗ 📜 NoSimple.java            
┃ ┃ ┣ 📂 duple/
┃ ┃ ┃ ┣ 📜 ListDoubleIncluded.java  
┃ ┃ ┃ ┗ 📜 NoDouble.java           
┃ ┃ ┗ 📂 circular/
┃ ┃ ┃ ┣ 📜 ListCircular.java        
┃ ┃ ┃ ┗ 📜 NoCircular.java          
┃ ┗ 📜 Main.java         
