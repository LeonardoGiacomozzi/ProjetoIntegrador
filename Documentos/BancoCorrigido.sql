CREATE SCHEMA IF NOT EXISTS `Tabacaria`  DEFAULT CHARACTER SET utf8 ;
USE `Tabacaria` ;

-- -----------------------------------------------------
-- Table `Tabacaria`.`Contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Contato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Telefone` VARCHAR(15) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `PessoaResponsavel` VARCHAR(45) NOT NULL,
  `CNPJ` VARCHAR(45) NOT NULL,
  `Contato` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Fornecedor_Contato1_idx` (`Contato` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Quantidade` INT(80) NOT NULL,
  `Valor` DOUBLE NOT NULL,
  `Fornecedor` INT NOT NULL,
  `Categoria` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Produtos_Fornecedor1_idx` (`Fornecedor` ASC) ,
  INDEX `fk_Produtos_Categoria1_idx` (`Categoria` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `CPF` VARCHAR(14) NOT NULL,
  `DataNascimento` DATE NOT NULL,
  `Endereco` VARCHAR(45) NOT NULL,
  `Contato` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cliente_Contato1_idx` (`Contato` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Cargo` INT NOT NULL,
  `CPF` VARCHAR(45) NOT NULL,
  `Contato` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Funcionario_Contato1_idx` (`Contato` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = armscii8;


-- -----------------------------------------------------
-- Table `Tabacaria`.`Venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Valor` DOUBLE NOT NULL,
  `Funcionario` INT NOT NULL,
  `Cliente` INT NOT NULL,
  `dataVenda` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Venda_Funcionário1_idx` (`Funcionario` ASC) ,
  INDEX `fk_Venda_Cliente1_idx` (`Cliente` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`ControleDeValores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`ControleDeValores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Entrada` DOUBLE NOT NULL,
  `Disponivel` DOUBLE NOT NULL,
  `Saidas` DOUBLE NOT NULL,
  `Venda` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Controle_de_Valores_Venda1_idx` (`Venda` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`NotaFiscal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`NotaFiscal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `DataEmissao` DATE NOT NULL,
  `Total` DOUBLE NOT NULL,
  `arquivo` BLOB NOT NULL,
  PRIMARY KEY (`id`)
  )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tabacaria`.`ItensPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`ItensPedido` (
  `Produtos` INT NOT NULL,
  `Venda` INT NOT NULL,
  PRIMARY KEY (`Produtos`, `Venda`),
  INDEX `fk_Produtos_has_Venda_Venda1_idx` (`Venda` ASC) ,
  INDEX `fk_Produtos_has_Venda_Produtos1_idx` (`Produtos` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Tabacaria`.`ItensPedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tabacaria`.`Usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
  );


