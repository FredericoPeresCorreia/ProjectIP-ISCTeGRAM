class EfeitoComposto {

	private	int[][] efeitos;
	private int iter;
	private ColorImage  img = EditorDeImagem.img;
	private EditorDeImagem editImage = new EditorDeImagem(img);

	public EfeitoComposto(){
		this.efeitos = new int[6][2];
		this.iter = 0;
		this.img = editImage.getImage();
	}

	public void addCompositeEffect(int effect){
		if(effect==EditorDeImagem.SEPIA){
			efeitos[iter][0] = effect;
			iter = iter+1;
		}
	}
	
	public void addCompositeEffect(int effect, int opc){
		if(effect==EditorDeImagem.NOISE){
			efeitos[iter][0] = effect;
			efeitos[iter][1] = opc;
			iter = iter+1;
		}
		if(effect==EditorDeImagem.CONTRAST){
			efeitos[iter][0] = effect;
			efeitos[iter][1] = opc;
			iter = iter+1;
		}
		if(effect==EditorDeImagem.VIGNETTE){
			efeitos[iter][0] = effect;
			efeitos[iter][1] = opc;
			iter = iter+1;
		}
		if(effect==EditorDeImagem.BLUR){
			efeitos[iter][0] = effect;
			efeitos[iter][1] = opc;
			iter = iter+1;
		}
		if(effect==EditorDeImagem.FILM){
			efeitos[iter][0] = effect;
			efeitos[iter][1] = opc;
			iter = iter+1;
		}
	}

	public void apply(){
		for(int x=0; x<iter; x++){
			if(efeitos[x][0]==EditorDeImagem.NOISE){
				editImage.noise(efeitos[x][1]);
			}
			if(efeitos[x][0]==EditorDeImagem.CONTRAST){
				editImage.contrast(efeitos[x][1]);
			}
			if(efeitos[x][0]==EditorDeImagem.VIGNETTE){
				editImage.vignette(efeitos[x][1]);
			}
			if(efeitos[x][0]==EditorDeImagem.SEPIA){
				editImage.sepia();
			}
			if(efeitos[x][0]==EditorDeImagem.BLUR){
				editImage.blur(efeitos[x][1]);
			}
			if(efeitos[x][0]==EditorDeImagem.FILM){
				editImage.film(efeitos[x][1]);
			}
		}
	}
	
}