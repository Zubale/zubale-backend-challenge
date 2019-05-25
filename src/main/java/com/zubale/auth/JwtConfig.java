package com.zubale.auth;


public class JwtConfig {
    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEA24nOrvotgsmSnxuUONqahXiNA8oFcBSGJmHUJtzjl+lqkJwR\n" +
            "cb8sKBAPQvkbZfNOzAJ8hbSZYZhqART/CovywyfpDt+8C6WCgENgM+zmS0xOcrIL\n" +
            "40zO7WiP2wqCuCcZXk4MN+3nIyRSKI7E94wAx5Wgrpi+RUf7mDnAdW7DF6r3GHJr\n" +
            "4M6+JMbyQHk5841U0WZi0IfV2WhaiiJKNJfgN4JzZjKXdBIlbzKj3x45Hk7hXmwa\n" +
            "cW6X91I1gMxpZJh3zzBuQvxwiiqqwqS+iJedyHRQgcFMXRi8ky1fWK/G5X0dYJlK\n" +
            "c8hqjyAOSZywxarL0VlXKoxSnMJfCkOMdAjSpQIDAQABAoIBAQC9clwi7szbMesO\n" +
            "1hPARBRX3UGC5ZBvuaZR9NXIe5EDBG/rPmJnT9tg7wrcdm7IOX/j2Llv//rkF0xS\n" +
            "hrXXehERi504MqXpPvgwivO3m1TX4Y9ItpA/o4ZlpHePQ7CuDT98+8jbcrakwrEL\n" +
            "QriCDhSWJm60fCalToq0QBHuxJgiENJEr9xNJc8MTUCM4kXY+nK1GsTQKJgbq89c\n" +
            "BgccZx2wMcTlSrl7bQHfKQEanjBz+IzKPzSHE8v//MgSQePVWnV7MeBX7LKcJoRz\n" +
            "eSC5WNSe8IyQu3R64ycAETss9ui34XG5xUe1SjztxQEZNBESsmh46Vecj4/rInJx\n" +
            "vBZlJBz5AoGBAP9c9OFZIaMbD4POpojx8+NtBR0VcXrLnx85V0ADdGxGqpDSVgFu\n" +
            "HCbv8/sW9vKxFUT93v/jwAAwj9tzkweh5+QBy18mS60qxst5bpEmprrW//3pXUD2\n" +
            "OKjBGgdeHOcdkxjMLZsWeJKKbHJNo5obQXNoJKleFeqewkH6PHdqCB5rAoGBANwV\n" +
            "+jyajUOo2p4jAqcIbJp4C8z0pp5kHA49cKDkIVDfF52F93GS9Gdn5sJ6EBpWHdKl\n" +
            "5kHOp/FguZsj4CuZG02IP6TTyeSH70pLxD2c7NlPv4omSHHR+jlOBe4b03rZ6U3x\n" +
            "MbpqfmUWNgJQDgJrRrPAsplLHOBDFaBFa+0eFvcvAoGARjnjIkHnzTo45w+intCj\n" +
            "OK0Xler6vUFYLjcPtov8rXY1nAQsINKOLWsZ1rh3C0EFz2ZduFOCzsqMFVbQXW36\n" +
            "HqsbqbwXXbUA3J41ePd/LKzJ06MkwSO1aztHlDUej7SDanNkK11fpTUqI7kUP+I0\n" +
            "+q7uPiGgt1gXphtSG6juOKcCgYBc0ggPtz8M4hIRz8I2oB7tpuOTNTtwYdhTSSZP\n" +
            "NOe4K97R2MZbaBcb5X41VzWjZd5+EOnjpzy9WIXvIFpTCOey81ukMI5di9coBnxl\n" +
            "X+GkZTXrFepH1r8pm/Z0TdgLYAw9ixPmAzP7fjqZDxpZetT+yq6IaF1tV92vEphK\n" +
            "EP6ukQKBgEUldy1v/BZ5G7avsOxhFK97VvMaAVbNxao4Pm4rIFrawSs0ObxMotVK\n" +
            "fUUqHs09iIUXDpEqQ/TjWo/9l2fhzmFdQ/OFLXHSgHR8LFLd+Diyb8TLLhyf5mDq\n" +
            "xGa/XN6W13qEoXjW0SBwUou5cj5LxoYNhxDFlj8cnU/J5pXh0PsM\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA24nOrvotgsmSnxuUONqa\n" +
            "hXiNA8oFcBSGJmHUJtzjl+lqkJwRcb8sKBAPQvkbZfNOzAJ8hbSZYZhqART/Covy\n" +
            "wyfpDt+8C6WCgENgM+zmS0xOcrIL40zO7WiP2wqCuCcZXk4MN+3nIyRSKI7E94wA\n" +
            "x5Wgrpi+RUf7mDnAdW7DF6r3GHJr4M6+JMbyQHk5841U0WZi0IfV2WhaiiJKNJfg\n" +
            "N4JzZjKXdBIlbzKj3x45Hk7hXmwacW6X91I1gMxpZJh3zzBuQvxwiiqqwqS+iJed\n" +
            "yHRQgcFMXRi8ky1fWK/G5X0dYJlKc8hqjyAOSZywxarL0VlXKoxSnMJfCkOMdAjS\n" +
            "pQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
