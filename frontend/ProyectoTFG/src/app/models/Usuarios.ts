export interface Usuarios {
    id: number;
    userName: string;
    password: string;
    email: string;
    nombre: string;
    apellidos: string;
    role?: string; // El rol es opcional, de momento
    nif: string;
    telefono: string;
    direccion: string;

}