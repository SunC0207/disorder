import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";

const schema = z.object({
  name: z
    .string({ required_error: "使用者名稱為必填欄位" })
    .min(3, { message: "至少要三個字" }),
  email: z
    .string({ required_error: "電子信箱為必填欄位" })
    .email({ message: "錯誤的電子信箱格式" }),
  password: z
    .string({ required_error: "密碼不得為空" })
    .min(6, { message: "最短要六個字" }),
});

type registerInfo = z.infer<typeof schema>;

interface Props {
  onSubmit: (data: registerInfo) => void;
}

const Form = ({ onSubmit }: Props) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<registerInfo>({ resolver: zodResolver(schema) });

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">
          使用者名稱
        </label>
        <input
          {...register("name")}
          id="name"
          type="text"
          className="form-control"
        />
        {errors.name && <p className="text-danger">{errors.name.message}</p>}
      </div>
      <div className="mb-3">
        <label htmlFor="email" className="form-label">
          電子信箱
        </label>
        <input
          {...register("email")}
          id="email"
          type="text"
          className="form-control"
        />
        {errors.email && <p className="text-danger">{errors.email.message}</p>}
      </div>
      <div className="mb-3">
        <label htmlFor="password" className="form-label">
          密碼
        </label>
        <input
          {...register("password")}
          type="password"
          className="form-control"
        />
        {errors.password && (
          <p className="text-danger">{errors.password.message}</p>
        )}
      </div>
      <button className="btn btn-primary">註冊</button>
    </form>
  );
};

export default Form;
