/**
 * Created by dev0 on 2/3/23.
 */
public class Item {
    private String name, room, description;
    private Integer damage, reuse;

    public Item() {}
    public Item(String name) {this.name = name;}
    public Item(String name, String room, String description, Integer damage, Integer reuse) {
        this.name = name;
        this.room = room;
        this.description = description;
        this.damage = damage;
        this.reuse = reuse;
    }

    @Override
    public boolean equals(Object other){
        if( this == other ) return true;
        if( !(other instanceof Item) ) return false;

        Item otherObj = (Item)other;
        Boolean nameEquals = ( this.name == null && otherObj.name == null );
        Boolean roomEquals = ( this.room == null && otherObj.room == null );
        Boolean descriptionEquals = ( this.room == null && otherObj.room == null );

        return nameEquals && roomEquals && descriptionEquals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getReuse() {
        return reuse;
    }

    public void setReuse(Integer reuse) {
        this.reuse = reuse;
    }
}