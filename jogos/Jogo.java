

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

public class Jogo {

	public static void main(String[] args) {
		
	        run();  
	}
	 public static void run() {
		   //Lista para ordenar por categoria
	        List<Item> listItens = new ArrayList<>();
	      //Lista para ordenar por avaliação
	        List<Item> listItensOrdPorCat = new ArrayList<>();
	      //Lista para ordenar por nome jogo
	        List<Item> listItensOrdPorJogo = new ArrayList<>();
	        Scanner sc = new Scanner(System.in);
	        
		    String arquivoCSV = "C:/Users/jvcgu/JogoOrdenadoAtividade/JogoOrdenado/jogos/JogoDesordenado/JogosDesordenados.csv";
		    BufferedReader br = null;
		    String linha = "";
		    String csvDivisor = ",";
		    try {
		     
		        
		        
		        //Leitura do arquivo
		        br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));
		        while ((linha = br.readLine()) != null) {
		           Item itens = new Item();
		               String[] linhaJogo = linha.split(csvDivisor);
		               
		                 itens.setNomeJogo(linhaJogo[0]);
		                 itens.setCategoria(linhaJogo[1]);
		                 itens.setAvaliacao(Double.valueOf(linhaJogo[2]).doubleValue());
		                 listItens.add(itens);
		             
		           
		        }
		        
		        int n = listItens.size()-1;    
		        Item temp;
		        //Ordenaçõ por categoria
		        for (int i = 0; i < n; i++) {
			          for (int j = i + 1; j <= n ; j++) {
			        	  Item itemOrd = listItens.get(i); 
					      String item1 = itemOrd.getCategoria(); 
			        	  Item itemOrd2 = listItens.get(j); 
			        	  String item2 = itemOrd2.getCategoria();
			        	   
		                
			        	   if (item1.compareTo(item2) > 0) {
			                   
			                    temp = listItens.get(i);
			                    listItens.set(i,listItens.get(j));
			                    listItens.set(j, temp);
			                }
		            }
		        }
		        listItensOrdPorCat = listItens;
		        
		        
		      
		      //Ordenaçõ por Avaliação em ordem decrescente
		        for (int a = 0; a < n; a++) {       
			          for (int b = a + 1; b <= n ; b++) {
			        	  Item itemOrd = listItensOrdPorCat.get(a); 
					      Double item1 = itemOrd.getAvaliacao(); 
			        	  Item itemOrd2 = listItensOrdPorCat.get(b); 
			        	  Double item2 = itemOrd2.getAvaliacao();
			        	  
			        	  String cat1 = itemOrd.getCategoria();  
			        	  String cat2 = itemOrd2.getCategoria();
			        	   
			        	  if (cat1.compareTo(cat2) == 0) {
			                   
			        		  if (item1 < (item2)) {
				                    temp = listItensOrdPorCat.get(a);
				                    listItensOrdPorCat.set(a,listItensOrdPorCat.get(b));
				                    listItensOrdPorCat.set(b, temp);
				                }
			                }
			        	  
		            }
			          
			        //Ordenaçõ por nome do jogo
			          listItensOrdPorJogo = listItensOrdPorCat;
				        for (int t = 0; t < n; t++) {       
					          for (int u = t + 1; u <= n ; u++) {
					        	  Item itemOrd = listItensOrdPorJogo.get(t); 
							      Double item1 = itemOrd.getAvaliacao(); 
					        	  Item itemOrd2 = listItensOrdPorJogo.get(u); 
					        	  Double item2 = itemOrd2.getAvaliacao();
					        	  
					        	  String cat1 = itemOrd.getCategoria();  
					        	  String cat2 = itemOrd2.getCategoria();
					        	  
					        	  String jogo1 =  itemOrd.getNomeJogo();
					        	  String jogo2 =  itemOrd2.getNomeJogo();
					        	   
					        	  if (cat1.compareTo(cat2) == 0) {
					                   
					        		  if (item1 == (item2)) {
					        			  
					        			  if (jogo1.compareTo(jogo2) > 0) {
							                   
							                    temp = listItensOrdPorJogo.get(t);
							                    listItensOrdPorJogo.set(t,listItensOrdPorJogo.get(u));
							                    listItensOrdPorJogo.set(u, temp);
							                }
						                }
					                }
					        	  
				            } 
			          
				        }
				        }
		        
		        
		        
		        for(int y=0; y<= n ; y++ ) {
		        	  System.out.println(listItensOrdPorJogo.get(y).getNomeJogo()+", "+listItensOrdPorJogo.get(y).getCategoria()+", "+ listItensOrdPorJogo.get(y).getAvaliacao());
		        }
		        
		        
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (br != null) {
		            try {
		                br.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    
		    //Salvando o Arquivo csv novo
		    
		    String arquivoDestinoStr = "C:/Users/jvcgu/JogoOrdenadoAtividade/JogoOrdenado/jogos/JogoDesordenado/JogoOrdenado.csv";
		    try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDestinoStr))){
                for(Item item : listItensOrdPorJogo){
                    bw.write(item.getNomeJogo()+","+item.getCategoria()+","+item.getAvaliacao());
                    bw.newLine();
                }
                System.out.println(arquivoDestinoStr+" FEITO!!!");
            }
            catch(IOException e){
                System.out.println("ERRO AO GRAVAR O ARQUIVO: "+e.getMessage());
            }
        sc.close();
		  
	 }

}
