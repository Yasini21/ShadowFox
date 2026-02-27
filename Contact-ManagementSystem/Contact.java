class Contact {
    String name;
    String email;
    int phoneNo;
    Contact(String name,String email,int phoneNo){
        this.name=name;
        this.email=email;
        this.phoneNo=phoneNo;
    }
    public void display(){
        System.out.println("Name: "+name);
        System.out.println("Email: "+email);
        System.out.println("PhoneNo: "+phoneNo);
    }
}
