class EditorDeImagem {

	public static  ColorImage img;
	private Color c;
	private int r;
	private int g;
	private int b;
	private final int MIN = 0;
	private final int MAX = 255;
	private double[][] sepiaValues = {{0.40,0.77,0.20},{0.35,0.69,0.17},{0.27,0.53,0.13}};
	public static final int NOISE = 0;
	public static final int CONTRAST = 1;
	public static final int VIGNETTE = 2;
	public static final int SEPIA = 3;
	public static final int BLUR = 4;
	public static final int FILM = 5;
	private ColorImage[] history = new ColorImage[999];
	private int iter = 0;
	private boolean redo;

	public EditorDeImagem(ColorImage img){
		EditorDeImagem.img = img.copy();
		history[iter] = img.copy();
		iter = iter+1;
		redo = false;
	}

	public ColorImage getImage(){
		return img;
	}

	public void noise(int n){
		redo = false;
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				int r1 = (int)(Math.random()*2);
				if(r1==1){
					int r2 = (int)(Math.random()*2);
					c = img.getColor(x, y);
					if(r2==0){
						r = c.getR()+n;
						g = c.getG()+n;
						b = c.getB()+n;
						if(r>MAX)
							r = MAX;
						if(r<MIN)
							r = MIN;
						if(g>MAX)
							g = MAX;
						if(g<MIN)
							g = MIN;
						if(b>MAX)
							b = MAX;
						if(b<MIN)
							b = MIN;
						c = new Color(r, g, b);
						img.setColor(x, y, c);
					}
					else{
						r = c.getR()-n;
						g = c.getG()-n;
						b = c.getB()-n;
						if(r>MAX)
							r = MAX;
						if(r<MIN)
							r = MIN;
						if(g>MAX)
							g = MAX;
						if(g<MIN)
							g = MIN;
						if(b>MAX)
							b = MAX;
						if(b<MIN)
							b = MIN;
						c = new Color(r, g, b);
						img.setColor(x, y, c);
					}
				}
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void contrast(int n){
		redo = false;
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				c = img.getColor(x,y);
				r = c.getR();
				g = c.getG();
				b = c.getB();
				if(c.getLuminance()>128){
					r = r+n;
					g = g+n;
					b = b+n;
				}
				else{
					r = r-n;
					g = g-n;
					b = b-n;
				}
				if(r>MAX){
					r = MAX;
				}
				if(r<MIN){
					r = MIN;
				}
				if(g>MAX){
					g = MAX;
				}
				if(g<MIN){
					g = MIN;
				}
				if(b>MAX){
					b = MAX;
				}
				if(b<MIN){
					b = MIN;
				}
				c = new Color(r,g,b);
				img.setColor(x, y, c);
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void vignette(int d){
		redo = false;
		if(d<0){
			throw new IllegalArgumentException("Introduza um valor positivo");
		}
		int cx = img.getWidth()/2;
		int cy = img.getHeight()/2;
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				int dc = (int)(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)));
				if(dc>d){
					c = img.getColor(x,y);
					r = c.getR()-(d*dc)/100;
					g = c.getG()-(d*dc)/100;
					b = c.getB()-(d*dc)/100;
					if(r>MAX){
						r = MAX;
					}
					if(r<MIN){
						r = MIN;
					}
					if(g>MAX){
						g = MAX;
					}
					if(g<MIN){
						g = MIN;
					}
					if(b>MAX){
						b = MAX;
					}
					if(b<MIN){
						b = MIN;
					}
					c = new Color(r, g, b);
					img.setColor(x, y, c);
				}
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void sepia(){
		redo = false;
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				c = img.getColor(x, y);
				r = (int)(c.getR()*sepiaValues[0][0] + c.getG()*sepiaValues[0][1] + c.getB()*sepiaValues[0][2]);
				g = (int)(c.getR()*sepiaValues[1][0] + c.getG()*sepiaValues[1][1] + c.getB()*sepiaValues[1][2]);
				b = (int)(c.getR()*sepiaValues[2][0] + c.getG()*sepiaValues[2][1] + c.getB()*sepiaValues[2][2]);
				if(r>MAX){
					r = MAX;
				}
				if(r<MIN){
					r = MIN;
				}
				if(g>MAX){
					g = MAX;
				}
				if(g<MIN){
					g = MIN;
				}
				if(b>MAX){
					b = MAX;
				}
				if(b<MIN){
					b = MIN;
				}
				c = new Color(r, g, b);
				img.setColor(x, y, c);
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void blur(int r){
		redo = false;
		if(r<0){
			throw new IllegalArgumentException("Introduza um valor positivo");
		}
		int it = 0;
		this.r = 0;
		b = 0;
		g = 0;
		ColorImage newImg = new ColorImage(img.getWidth(), img.getHeight());
		newImg = img.copy();
		for(int x=0; x<img.getWidth(); x++){
			for(int y=0; y<img.getHeight(); y++){
				for(int i=x-r; i<x+r;i++){
					for(int j=y-r; j<y+r; j++){
						if(j>=0 && i>=0 && j<img.getHeight() && i<img.getWidth()){
							c = newImg.getColor(i,j);
							this.r = this.r+c.getR();
							g = g+c.getG();
							b = b+c.getB();
							it = it+1;
						}
					}
				}
				this.r = this.r/it;
				g = g/it;
				b = b/it;
				it = 0;
				if(r>MAX){
					r = MAX;
				}
				if(r<MIN){
					r = MIN;
				}
				if(g>MAX){
					g = MAX;
				}
				if(g<MIN){
					g = MIN;
				}
				if(b>MAX){
					b = MAX;
				}
				if(b<MIN){
					b = MIN;
				}
				c = new Color(this.r, g, b);
				this.r=0;
				g=0;
				b=0;
				img.setColor(x, y, c);
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void film(int l){
		redo = false;
		if(l<0){
			throw new IllegalArgumentException("Introduza um valor positivo");
		}
		for(int x=0; x<l; x++){
			for(int y=0; y<img.getHeight(); y++){
				img.setColor(x,y,Color.BLACK);
				if(x>l/4 && x<(3*l/4)){
					img.setColor(x,y,Color.WHITE);
				}
				if(y<(img.getHeight()/10) || (y<img.getHeight()/3 && y>img.getHeight()/4) ||  (y<((2*img.getHeight()/4)+((img.getHeight()/3)-(img.getHeight()/4))) && y>2*img.getHeight()/4) || (y<((3*img.getHeight()/4)+((img.getHeight()/3)-(img.getHeight()/4))) && y>3*img.getHeight()/4) ||  (y<img.getHeight() && y>((img.getHeight())-((img.getHeight()/3)-(img.getHeight()/4)))+10)){
					img.setColor(x,y,Color.BLACK);
				}
			}
		}
		for(int i=img.getWidth()-l-1; i<img.getWidth(); i++){
			for(int j=0; j<img.getHeight(); j++) {
				img.setColor(i,j,Color.BLACK);
				if(i>img.getWidth()-(3*l/4) && i<img.getWidth()-l/4){
					img.setColor(i,j,Color.WHITE);
				}
				if(j<(img.getHeight()/10) || (j<img.getHeight()/3 && j>img.getHeight()/4) ||  (j<((2*img.getHeight()/4)+((img.getHeight()/3)-(img.getHeight()/4))) && j>2*img.getHeight()/4) || (j<((3*img.getHeight()/4)+((img.getHeight()/3)-(img.getHeight()/4))) && j>3*img.getHeight()/4) ||  (j<img.getHeight() && j>((img.getHeight())-((img.getHeight()/3)-(img.getHeight()/4)))+10)){
					img.setColor(i,j,Color.BLACK);
				}
			}
		}
		history[iter] = img.copy();
		iter = iter+1;
	}

	public void applyCompositeEffect(EfeitoComposto c){
		redo = false;
		c.apply();
		history[iter] = img.copy();
		iter = iter+1;
		//Após ser criado um objeto do tipo  EfeitoComposto é necessário utilizar 
		//o procedimento getImage para visualizar o efeito composto a ser aplicado
	}

	public void undo(){
		redo = true;
		if(iter<=1){
			for(int z=0; z<history[0].getWidth(); z++){
				for(int y=0; y<history[0].getHeight(); y++){
					img.setColor(z,y,history[0].getColor(z,y));
				}
			}
		}
		else{
			for(int z=0; z<history[iter-2].getWidth(); z++){
				for(int y=0; y<history[iter-2].getHeight(); y++){
					img.setColor(z,y,history[iter-2].getColor(z,y));
				}
			}
			iter = iter-1;
		}
	}

	public void redo(){
		if(redo==true){
			if(history[iter]==null || iter>=history.length){
				for(int z=0; z<history[iter-1].getWidth(); z++){
					for(int y=0; y<history[iter-1].getHeight(); y++){
						img.setColor(z,y,history[iter-1].getColor(z,y));
					}
				}
				
			}
			else{
				for(int z=0; z<history[iter].getWidth(); z++){
					for(int y=0; y<history[iter].getHeight(); y++){
						img.setColor(z,y,history[iter].getColor(z,y));
					}
				}
			}
			iter = iter+1;
		}
	}

}