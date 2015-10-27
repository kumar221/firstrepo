package codingInterview;

public class matrixRotate {
public int[][] rotate(int[][] matrix){
	int matrixsize=matrix.length;
	
	int[][] buffermatrix=new int[matrixsize][matrixsize];
	for(int i=0;i<matrixsize;i++){
		for(int j=0;j<matrixsize;j++){
			buffermatrix[i][j]=matrix[matrixsize-j-1][i];
		}
	}
	for(int i=0;i<matrixsize;i++){
		for(int j=0;j<matrixsize;j++){
			matrix[i][j]=buffermatrix[i][j];
		}
	}
	return matrix;
}

public static void main(String args[]){
	matrixRotate mr=new matrixRotate();
	int matrix[][]={{1,2,3},{4,5,6},{7,8,9}};
	int[][] mt=mr.rotate(matrix);
	for(int i=0; i<mt.length;i++){
		for(int j=0; j<mt.length;j++){
			System.out.print(mt[i][j]+"");
		}
		System.out.println();
	}
}
}
