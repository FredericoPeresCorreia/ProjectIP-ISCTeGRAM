class Test {
	static void test(){
		ColorImage ii = new ColorImage("3.jpg");
		EditorDeImagem i = new EditorDeImagem(ii);
		EfeitoComposto oldPhoto = new EfeitoComposto();
		oldPhoto.addCompositeEffect(EditorDeImagem.NOISE,10);
		oldPhoto.addCompositeEffect(EditorDeImagem.SEPIA);
		oldPhoto.addCompositeEffect(EditorDeImagem.VIGNETTE, 40);
		i.applyCompositeEffect(oldPhoto);
		EfeitoComposto retro = new EfeitoComposto();
		retro.addCompositeEffect(EditorDeImagem.CONTRAST, 40);
		retro.addCompositeEffect(EditorDeImagem.BLUR, 1);
		retro.addCompositeEffect(EditorDeImagem.VIGNETTE, 40);
		i.applyCompositeEffect(retro);
	}


}