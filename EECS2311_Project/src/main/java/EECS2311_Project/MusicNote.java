package EECS2311_Project;

public class MusicNote {
    public String note;
    public String modifier;
    public int position;

    public MusicNote (String note, int position){
        this.note = note;
        this.position = position;
    }

    public void addModifier(String modifier){
        this.modifier = modifier;
    }
}
