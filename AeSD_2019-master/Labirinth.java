// Spanning Tree of a given connected, undirected and
import java.util.*;
import java.io.*;

class Labirinth {

public static final String OUT_FILE = "labirinth.svg";
public static final String DEFINITIONS = "hex_definitions.txt";
public static final String DEFINITIONSQ = "hex_definitionsQ.txt";

    private static int coord_to_id(int i, int j, int cols){
        return i*cols+j;
    }

    private static int[] id_to_coord(int id, int cols){
        return new int[]{id/cols,id%cols};
    }
    private static int[] guardaLatoQ ( int id1, int id2, int rows, int cols ){
        int i1, i2, j1, j2;
        int[] tmp;
        tmp = id_to_coord( id1, cols );
        i1 = tmp[0];
        j1 = tmp[1];
        tmp = id_to_coord( id2, cols );
        i2 = tmp[0];
        j2 = tmp[1];


        if ( j1 == j2 ){
            if ( i1 - 1 == i2 ){
                return new int[]{0,2};
            }
            else {
                return new int[]{2,0};
            }
        }

        else {
            if ( j1 - 1 == j2 ){
                return new int[]{1,3};
            }
            else {
                return new int[]{3,1};
            }
        }
    }
    private static void print_svgQ(int[][] conf, String fout){
        BufferedReader br = null;
        FileWriter fw = null;
        try{
            br = new BufferedReader(new FileReader(DEFINITIONSQ));
            fw = new FileWriter(OUT_FILE);

            int rows, cols, dimX, dimY;
            rows = conf.length;
            cols = conf[0].length;
            dimX = rows*10+15;
            dimY = cols*10+25;

            //first line
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.flush();

            //second line
            fw.write("<svg viewBox=\"0 0 ");
            fw.write(String.valueOf(dimX));
            fw.write(" ");
            fw.write(String.valueOf(dimY));
            fw.write("\"> \n");
            fw.flush();

            String str;
            while( (str = br.readLine()) != null){
                fw.write(str);
                fw.write("\n");
                fw.flush();

            }
            double x_svg =0d, y_svg =0d;
            for(int r=0; r<rows; r++){
                for(int c=0; c<cols; c++){

                    x_svg = 10 * c + 10;
                    y_svg = 10 * r + 20;

                    fw.write("<use xlink:href=\"#s");
                    String supporto = "" + conf[r][c];
                    fw.write(supporto);
                    fw.write("\" transform=\"translate(");
                    fw.write(String.valueOf(x_svg));
                    fw.write(",");
                    fw.write(String.valueOf(y_svg));
                    fw.write(")\"/>\n");
                    fw.flush();
                }
            }
            //last arrow
            fw.write("<use xlink:href=\"#down\" transform=\"translate(");
            fw.write(String.valueOf(x_svg-3.5));
            fw.write(",");
            fw.write(String.valueOf(y_svg+7));
            fw.write(")\"/>\n");
            fw.flush();

            //end file
            fw.write("</g></svg>\n");
            fw.flush();

            br.close();
            fw.close();


        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    private static void print_svg(int[][] conf, String fout){
        BufferedReader br = null;
        FileWriter fw = null;
        try{
            br = new BufferedReader(new FileReader(DEFINITIONS));
            fw = new FileWriter(OUT_FILE);

            int rows, cols, dimX, dimY;
            rows = conf.length;
            cols = conf[0].length;
            dimX = cols*15+5;
            dimY = 18*rows+30;

            //first line
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.flush();

            //second line
            fw.write("<svg viewBox=\"0 0 ");
            fw.write(String.valueOf(dimX));
            fw.write(" ");
            fw.write(String.valueOf(dimY));
            fw.write("\"> \n");
            fw.flush();

            //definitions
            String str;
            while( (str = br.readLine()) != null){
                fw.write(str);
                fw.write("\n");
                fw.flush();
            }

            //placing multilines
            double x_svg =0d, y_svg =0d;
            for(int r=0; r<rows; r++){
                for(int c=0; c<cols; c++){

                    x_svg = 15 * c + 10;
                    y_svg = 18 * r + 20;
                    if(c%2 == 1)
                        y_svg += 9;

                    fw.write("<use xlink:href=\"#s");
                    String supporto = "" + conf[r][c];
                    fw.write(supporto);//fw.write(conf[r][c]); //QUESTA è GIUSTA!!!
                    fw.write("\" transform=\"translate(");
                    fw.write(String.valueOf(x_svg));
                    fw.write(",");
                    fw.write(String.valueOf(y_svg));
                    fw.write(")\"/>\n");
                    fw.flush();
                }
            }


            //last arrow
            fw.write("<use xlink:href=\"#down\" transform=\"translate(");
            fw.write(String.valueOf(x_svg-3.5));
            fw.write(",");
            fw.write(String.valueOf(y_svg+7));
            fw.write(")\"/>\n");
            fw.flush();

            //end file
            fw.write("</g></svg>\n");
            fw.flush();

            br.close();
            fw.close();
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }


    private static int[] guardaLato ( int id1, int id2 , int rows, int cols ){
        int i1, i2, j1, j2;
        int[] tmp;
        tmp = id_to_coord( id1,cols );
        i1 = tmp[0];
        j1 = tmp[1];
        tmp = id_to_coord( id2,cols );
        i2 = tmp[0];
        j2 = tmp[1];

        if ( j1 == j2 ){
            if ( i1 + 1 == i2 ){
                return new int[]{3,0};
            }
            else {
                return new int[]{0,3};
            }

        }

        else if ( j1 % 2 == 0 ){
            if ( i1 == i2 ){
                if ( j1 + 1 == j2 ){
                    return new int[]{4,1};

                }
                else {
                    return new int[]{2,5};
                }
            }


            else {
                if ( j1 - 1 == j2 ){
                    return new int[]{1,4};
                }
                else {
                    return new int[]{5,2};
                }
            }
        }

        else {
            if ( i1 == i2 ){
                if ( j1 + 1 == j2 ){
                    return new int[]{5,2};
                }
                else {
                    return new int[]{1,4};
                }
            }

            else{
                if ( j1 - 1 == j2 ){
                    return new int[]{2,5};
                }

                else {
                    return new int[]{4,1};
                }
            }

        }
    }

    private static int[][] assegnaNumeroQ ( Edge[] result, int rows, int cols ){

        int[][] numero = new int[rows][cols];

        for ( int i = 0; i < rows; ++i ){
            for ( int j = 0; j < cols; ++j ){
                numero[i][j] = 15;
            }
        }

        for ( int i = 0; i < result.length - 1; ++i ){
            int v1 = result[i].src;
            int v2 = result[i].dest;

            int[] confine = guardaLatoQ ( v1, v2, rows, cols );

            numero[v1/cols][v1%cols] &= ~( 1 << confine[0] );
            numero[v2/cols][v2%cols] &= ~( 1 << confine[1] );
        }

        numero[0][0] -= 1;
        numero[rows - 1][cols - 1] -= 4;

        /*for( int i = 0; i < rows; ++i ){
            for ( int j = 0; j < cols; ++j ){
                System.out.println( "Peso nodo coordinate (" + i +", " + j + ") = " + numero[i][j]);
            }
        }*/



        return numero;
    }

    private static int[][] assegnaNumero ( Edge[] result, int rows, int cols){

        int[][] numero = new int[rows][cols];

        for ( int i = 0; i < rows; ++i ){
            for ( int j = 0; j < cols; ++j ){
                numero[i][j] = 63;
            }
        }

        for ( int i = 0; i < result.length - 1; ++i ){
            int v1 = result[i].src;
            int v2 = result[i].dest;

            int[] confine = guardaLato ( v1, v2, rows, cols );

            numero[v1/cols][v1%cols] &= ~( 1 << confine[0] );
            numero[v2/cols][v2%cols] &= ~( 1 << confine[1] );

        }

        numero[0][0] -= 1;
        numero[rows - 1][cols - 1] -= 8;

        /*for ( int i = 0; i < rows; ++i ){
            for ( int j = 0; j < cols; ++j ){
                System.out.println("peso nodo coordinate (" + i + " " + j + ") = " + numero[i][j]);
            }
        }*/
        return numero;


    }

    // Driver Program
    public static void main (String[] args)
    {


        long inizio2 = System.currentTimeMillis();
        int rows,cols,isHexagonal;
        long seed;
        if (args.length != 4)
        {
            System.out.println("Usage is: "+ Labirinth.class.getName()+" <rows> <cols> <seed> <isHexagonal>");
            return;
        } else
        {
                rows = Integer.valueOf(args[0]);
                cols = Integer.valueOf(args[1]);
                seed = Long.valueOf(args[2]);
                isHexagonal = Integer.valueOf(args[3]);
        }

        Random random = new Random(seed);

        if ( isHexagonal == 1 )
        {

            int V = rows*cols;  // Number of vertices in graph
            int E = V*3;  // Number of edges in graph
            Graph graph = new Graph(V, E);

            int num_edge = 0;
            for (int r=0;r<rows;r++){
                for (int c=0;c<cols;c++){
                    int v1,v2;
                    v1 = coord_to_id(r,c,cols);

                    //R-1,C
                    if(r>0){
                        v2 = coord_to_id(r-1,c,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                    if(c>0){
                        v2 = coord_to_id(r,c-1,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                    if (c%2 == 0 && r > 0 && c > 0 ){
                        v2 = coord_to_id(r-1,c-1,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                    if (c%2 != 0 && r + 1 < rows && c > 0){
                        v2 = coord_to_id(r+1,c-1,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                }
            }
            long inizio = System.currentTimeMillis();
            Edge[] result = graph.KruskalMST();
            long fine = System.currentTimeMillis();
            System.out.println( fine - inizio );
            int[][] conf = assegnaNumero( result, rows, cols );
            print_svg(conf,OUT_FILE);
        }

        if ( isHexagonal == 0 ){

            int V = rows*cols;
            int E = V*2;
            Graph graph = new Graph(V, E);
            int num_edge = 0;

            for (int r = 0; r < rows; ++r){
                for (int c = 0; c < cols; ++c){
                    int v1, v2;
                    v1 = coord_to_id(r,c,cols);
                    if ( r > 0 ){
                        v2 = coord_to_id(r-1,c,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                    if ( c > 0 ){
                        v2 = coord_to_id(r,c-1,cols);
                        graph.edge[num_edge].src = v1;
                        graph.edge[num_edge].dest = v2;
                        graph.edge[num_edge].weight = random.nextInt();
                        num_edge++;
                    }
                }
            }
            Edge[] result = graph.KruskalMST();
            int[][] conf  = assegnaNumeroQ ( result, rows, cols );
            print_svgQ(conf,OUT_FILE);

        }
        long fine2 = System.currentTimeMillis();
        System.out.println( fine2 - inizio2 );

    }
}