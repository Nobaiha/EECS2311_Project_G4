package EECS2311_Project;

public class MusicNote {
    public String note;
    public String modifier;

    public MusicNote (String note){
        this.note = note;
    }

    public void addModifier(String modifier){
        this.modifier = modifier;
    }
}
